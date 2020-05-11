package com.aryun.gmall.search.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.aryun.gmall.bean.PmsSearchParam;
import com.aryun.gmall.bean.PmsSearchSkuInfo;
import com.aryun.gmall.bean.PmsSkuAttrValue;
import com.aryun.gmall.service.SearchService;
import io.searchbox.annotations.JestId;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.redis.core.query.SortQueryBuilder;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    JestClient jestClient;

    /**
     * 查询es数据
     * @param pmsSearchParam
     * @return
     */
    @Override
    public List<PmsSearchSkuInfo> list(PmsSearchParam pmsSearchParam) {
        //3级分类id
        String catalog3Id=pmsSearchParam.getCatalog3Id();
        //模糊搜索关键字
        String keyword=pmsSearchParam.getKeyword();
        //属性值id

        String[] pmsSkuAttrValueStr=pmsSearchParam.getValueId();


        //创建查询
        BoolQueryBuilder queryBuilder= QueryBuilders.boolQuery();
        if(pmsSkuAttrValueStr!=null && pmsSkuAttrValueStr.length>0){
            for(String str :pmsSkuAttrValueStr){
                queryBuilder.filter(QueryBuilders.termQuery("skuAttrValueList.valueId",str));
            }
        }
        if(StringUtils.isNotEmpty(catalog3Id)){
            queryBuilder.filter(QueryBuilders.termQuery("catalog3Id",catalog3Id));
        }
        if(StringUtils.isNotEmpty(keyword)){

            queryBuilder.must(QueryBuilders.matchQuery("skuName",keyword));
        }

        //封装类
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //封装查询条件
        nativeSearchQueryBuilder.withQuery(queryBuilder);
        //设置排序
        SortBuilder sortBuilder = SortBuilders.fieldSort("price").order(SortOrder.DESC);
        //设置高亮
        String preTags = "<span style='color:red;'>";
        String postTags = "</span>";
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags(preTags);//设置前缀
        highlightBuilder.field("skuName");//设置高亮字段
        highlightBuilder.postTags(postTags);//设置后缀
        //封装
        nativeSearchQueryBuilder.withPageable(PageRequest.of(0,100)).withSort(sortBuilder).withHighlightBuilder(highlightBuilder);
        //查询，配置高亮
        List<PmsSearchSkuInfo> pmsSearchSkuInfos= elasticsearchTemplate.query(nativeSearchQueryBuilder.build(),p->{
            List<PmsSearchSkuInfo>  pmsSearchSkuInfoList=new ArrayList<>();
            p.getHits().forEach((p1)->{
                //高亮部分设置
                HighlightField highlightField= p1.getHighlightFields().get("skuName");
                String strSkuName="";
                System.out.println("输出strName:"+strSkuName);
                if(highlightField!=null){
                    Text[] texts= highlightField.fragments();
                    System.out.println("输出文本："+texts.toString());

                    for(int i=0;i<texts.length;i++){
                        strSkuName+=texts[i];
                    }

                }

                String str= p1.getSourceAsString();
                //这里第一个是基本信息不是PmsSearchSkuInfo数据
                PmsSearchSkuInfo pmsSearchSkuInfo= JSONObject.parseObject(str,PmsSearchSkuInfo.class);
                //是否有高亮查询功能
                if(StringUtils.isNotBlank(strSkuName)){
                    pmsSearchSkuInfo.setSkuName(strSkuName);
                }
                pmsSearchSkuInfoList.add(pmsSearchSkuInfo);
            });
            return pmsSearchSkuInfoList;
        });
        return pmsSearchSkuInfos;
    }

    /**
     * 另一种实现方式可用
     * @param pmsSearchParam
     * @return
     */
    @Override
    public List<PmsSearchSkuInfo> listTest(PmsSearchParam pmsSearchParam) {
        String dslStr = getSearchDsl(pmsSearchParam);
        System.err.println(dslStr);
        // 用api执行复杂查询
        List<PmsSearchSkuInfo> pmsSearchSkuInfos = new ArrayList<>();
        Search search = new Search.Builder(dslStr).addIndex("gmallpms").addType("PmsSkuInfo").build();
        SearchResult execute = null;
        try {
            execute = jestClient.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<SearchResult.Hit<PmsSearchSkuInfo, Void>> hits = execute.getHits(PmsSearchSkuInfo.class);
        for (SearchResult.Hit<PmsSearchSkuInfo, Void> hit : hits) {
            PmsSearchSkuInfo source = hit.source;
            Map<String, List<String>> highlight = hit.highlight;
            String skuName = highlight.get("skuName").get(0);
            source.setSkuName(skuName);
            pmsSearchSkuInfos.add(source);
        }

        System.out.println(pmsSearchSkuInfos.size());
        return pmsSearchSkuInfos;
    }

    private String getSearchDsl(PmsSearchParam pmsSearchParam) {

        String[] skuAttrValues= pmsSearchParam.getValueId();
        String keyword = pmsSearchParam.getKeyword();
        String catalog3Id = pmsSearchParam.getCatalog3Id();

        // jest的dsl工具
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // bool
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        // filter
        if(StringUtils.isNotBlank(catalog3Id)){
            TermQueryBuilder termQueryBuilder = new TermQueryBuilder("catalog3Id",catalog3Id);
            boolQueryBuilder.filter(termQueryBuilder);
        }
        if(skuAttrValues!=null){
            for (String pmsSkuAttrValueStr : skuAttrValues) {
                TermQueryBuilder termQueryBuilder = new TermQueryBuilder("skuAttrValueList.valueId",pmsSkuAttrValueStr);
                boolQueryBuilder.filter(termQueryBuilder);
            }
        }

        // must
        if(StringUtils.isNotBlank(keyword)){
            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("skuName",keyword);
            boolQueryBuilder.must(matchQueryBuilder);
        }

        // 封装查询
        searchSourceBuilder.query(boolQueryBuilder);

        // highlight
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.preTags("<span style='color:red;'>");
//        highlightBuilder.field("skuName");
//        highlightBuilder.postTags("</span>");
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field highlightTitle =
                new HighlightBuilder.Field("skuName");
        highlightTitle.highlighterType("unified");
        highlightBuilder.field(highlightTitle);


        searchSourceBuilder.highlighter(highlightBuilder);
        // sort
        searchSourceBuilder.sort("id",SortOrder.DESC);
        // from
        searchSourceBuilder.from(0);
        // size
        searchSourceBuilder.size(20);

        return searchSourceBuilder.toString();

    }
}
