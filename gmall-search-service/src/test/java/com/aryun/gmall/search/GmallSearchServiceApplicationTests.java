package com.aryun.gmall.search;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aryun.gmall.bean.PmsSearchParam;
import com.aryun.gmall.bean.PmsSkuAttrValue;
import com.aryun.gmall.bean.PmsSkuInfo;
import com.aryun.gmall.search.GmallSearchServiceApplication;
import com.aryun.gmall.service.PmsSkuService;

//import io.searchbox.client.JestClient;
//import io.searchbox.core.Index;
import com.aryun.gmall.service.SearchService;
import io.reactivex.Completable;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.apache.dubbo.config.annotation.Reference;


import org.apache.lucene.index.Term;
import org.apache.lucene.search.TermQuery;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MatchQuery;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@SpringBootTest(classes = {GmallSearchServiceApplication.class})
public class GmallSearchServiceApplicationTests  {
    @Reference
    PmsSkuService pmsSkuService;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
   ElasticsearchRepository elasticsearchRepository;
    @Autowired
    JestClient jestClient;
   @Autowired
   pmsInter pmsInter;
   @Reference
   SearchService searchService;

    /**
     * jestClient 实现
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        // jest的dsl工具
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        System.out.println("测试");
        BoolQueryBuilder queryBuilder=QueryBuilders.boolQuery();
        // 用api执行复杂查询
        List<PmsSearchSkuInfo> pmsSearchSkuInfos = new ArrayList<>();
        searchSourceBuilder.query(queryBuilder);
        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex("gmallpms").addType("PmsSkuInfo").build();

        SearchResult execute = jestClient.execute(search);

        List<SearchResult.Hit<PmsSearchSkuInfo, Void>> hits = execute.getHits(PmsSearchSkuInfo.class);

        for (SearchResult.Hit<PmsSearchSkuInfo, Void> hit : hits) {
            PmsSearchSkuInfo source = hit.source;

            pmsSearchSkuInfos.add(source);
        }
        System.out.println(pmsSearchSkuInfos.size());
    }
    /**
     *存储数据
     */
    @Test
    public void data() throws IOException {
        //查询mysql数据
        List<PmsSkuInfo> pmsSkuInfoList =pmsSkuService.getAllSku("61");
        //转化为es数据结构
        List<PmsSearchSkuInfo> searchSkuInfos=new ArrayList();
        for(PmsSkuInfo pmsSkuInfo:pmsSkuInfoList){
            PmsSearchSkuInfo pmsSearchSkuInfo=new PmsSearchSkuInfo();
            BeanUtils.copyProperties(pmsSkuInfo,pmsSearchSkuInfo);
            searchSkuInfos.add(pmsSearchSkuInfo);
        }

        //导入es
        for(PmsSearchSkuInfo pmsSearchSkuInfo:searchSkuInfos) {
            IndexQuery indexQuery = new IndexQueryBuilder().withId(pmsSearchSkuInfo.getId()).withObject(pmsSearchSkuInfo).build();
            elasticsearchTemplate.index(indexQuery);
        }
    }
    /**
     *查询数据
     * elasticsearchTemplate实现
     * ElasticSearchTemplate更多是对ESRepository的补充，里面提供了一些更底层的方法。
     */
    @Test
    public void put() throws IOException {
       List list= new ArrayList<String>();
        list.add("40");
        list.add("46");
        BoolQueryBuilder queryBuilder=QueryBuilders.boolQuery();
        queryBuilder.filter(QueryBuilders.termQuery("skuAttrValueList.valueId","40"));
        queryBuilder.filter(QueryBuilders.termQuery("skuAttrValueList.valueId","46"));
        queryBuilder.must(QueryBuilders.matchQuery("skuName","三星"));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(queryBuilder);
        List<PmsSearchSkuInfo> pmsSearchSkuInfoList=elasticsearchTemplate.queryForList(nativeSearchQueryBuilder.build(),PmsSearchSkuInfo.class);
        pmsSearchSkuInfoList.forEach((p)->{
            System.out.println("开始输出============================");
            System.out.println(p.toString());
            System.out.println(p.getSkuAttrValueList().toString());
            System.out.println("结束输出============================");
        });

    }

    /**
     *查询数据
     * springbootdata实现
     */
    @Test
    public void putRepository() throws IOException {
        List list= new ArrayList<String>();
        list.add(40);
        list.add(46);
        BoolQueryBuilder queryBuilder=QueryBuilders.boolQuery();
        //queryBuilder.filter(QueryBuilders.termQuery("skuAttrValueList.valueId","40"));
        //queryBuilder.filter(QueryBuilders.termQuery("skuAttrValueList.valueId","46"));
        //queryBuilder.must(QueryBuilders.matchQuery("skuName","三星"));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(queryBuilder);
        Iterable<PmsSearchSkuInfo> items = pmsInter.search(nativeSearchQueryBuilder.build());
        Iterator<PmsSearchSkuInfo> pmsSearchSkuInfos=items.iterator();
        while (pmsSearchSkuInfos.hasNext()) {
            System.out.println("开始输出============================");
            PmsSearchSkuInfo pmsSearchSkuInfo = pmsSearchSkuInfos.next();
            System.out.println(pmsSearchSkuInfo.toString());
            System.out.println("结束输出============================");
        }

    }
    /**
     *注解方式
     *
     *
     */
    @Test
    public void dataTwo() throws IOException {
        TaskInfo taskInfo=new TaskInfo();
        taskInfo.setTaskArea("12312311");
        taskInfo.setTaskContent("12312311");
        taskInfo.setTaskId("111");
        taskInfo.setTaskState("241");
        taskInfo.setUpdateTime("");
        taskInfo.setUserId(661);
        taskInfo.setUserNickName("rui11");


            IndexQuery query = new IndexQueryBuilder().withId(taskInfo.getTaskId()).withObject(taskInfo).build();
            elasticsearchTemplate.index(query);



    }

    /**
     *分页
     * @throws IOException
     */
    @Test
    public void getSearch() throws IOException {
        BoolQueryBuilder queryBuilder=QueryBuilders.boolQuery();
        queryBuilder.filter(QueryBuilders.termQuery("skuAttrValueList.valueId","40"));
        queryBuilder.filter(QueryBuilders.termQuery("skuAttrValueList.valueId","46"));
        queryBuilder.must(QueryBuilders.matchQuery("skuName","三星"));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(queryBuilder);
        //0为第一页
        nativeSearchQueryBuilder.withPageable(PageRequest.of(1,2));
        //自带分页
        Page<PmsSearchSkuInfo> pmsSearchSkuInfoPage= elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), PmsSearchSkuInfo.class);
        //测试代码
        List<PmsSearchSkuInfo> pmsSearchSkuInfo =pmsSearchSkuInfoPage.getContent();
        pmsSearchSkuInfo.forEach((p)->{
            System.out.println("开始==========");
            System.out.println(p.toString());
            System.out.println("结束==========");
        });
    }

    /**
     *lamada获取全部数据
     * @throws IOException
     */
    @Test
    public void getSearchLamada() throws IOException {
        BoolQueryBuilder queryBuilder=QueryBuilders.boolQuery();
        //queryBuilder.filter(QueryBuilders.termQuery("skuAttrValueList.valueId","40"));
        //queryBuilder.filter(QueryBuilders.termQuery("skuAttrValueList.valueId","46"));
        //queryBuilder.must(QueryBuilders.matchQuery("skuName","三星"));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(queryBuilder);

        //这个可以通过lameda表达实现返回值封装
        List<PmsSearchSkuInfo> pmsSearchSkuInfos= elasticsearchTemplate.query(nativeSearchQueryBuilder.build(),p->{
            List<PmsSearchSkuInfo>  pmsSearchSkuInfoList=new ArrayList<>();
            p.getHits().forEach((p1)->{

               String str= p1.getSourceAsString();
               //这里第一个是基本信息不是PmsSearchSkuInfo数据
                PmsSearchSkuInfo pmsSearchSkuInfo=JSONObject.parseObject(str,PmsSearchSkuInfo.class);
                pmsSearchSkuInfoList.add(pmsSearchSkuInfo);
            });
            return pmsSearchSkuInfoList;
        });

        pmsSearchSkuInfos.forEach((p)->{
            System.out.println("开始==========");
            System.out.println(p.toString());
            System.out.println("结束==========");
        });
    }

    /**
     *高亮
     * @throws IOException
     */
    @Test
    public void getSearchLamada123() throws IOException {
        BoolQueryBuilder queryBuilder=QueryBuilders.boolQuery();
        queryBuilder.filter(QueryBuilders.termQuery("skuAttrValueList.valueId","40"));
        queryBuilder.filter(QueryBuilders.termQuery("skuAttrValueList.valueId","46"));
        queryBuilder.must(QueryBuilders.matchQuery("skuName","三星"));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(queryBuilder);

        String preTags = "<span style='color:red;'>";
        String postTags = "</span>";
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags(preTags);//设置前缀
        highlightBuilder.field("skuName");//设置高亮字段
        highlightBuilder.postTags(postTags);//设置后缀
        nativeSearchQueryBuilder.withHighlightBuilder(highlightBuilder);
        nativeSearchQueryBuilder.withPageable(PageRequest.of(0,100));
        //这个可以通过lameda表达实现返回值封装
        List<PmsSearchSkuInfo> pmsSearchSkuInfos= elasticsearchTemplate.query(nativeSearchQueryBuilder.build(),p->{
            List<PmsSearchSkuInfo>  pmsSearchSkuInfoList=new ArrayList<>();
            p.getHits().forEach((p1)->{

               HighlightField highlightField= p1.getHighlightFields().get("skuName");
               Text[] texts= highlightField.fragments();
                System.out.println("输出文本："+texts.toString());
               String strSkuName="";
                for(int i=0;i<texts.length;i++){
                    strSkuName+=texts[i];
                }
                System.out.println("输出strName:"+strSkuName);
                String str= p1.getSourceAsString();
                //这里第一个是基本信息不是PmsSearchSkuInfo数据
                PmsSearchSkuInfo pmsSearchSkuInfo=JSONObject.parseObject(str,PmsSearchSkuInfo.class);
                pmsSearchSkuInfo.setSkuName(strSkuName);
                pmsSearchSkuInfoList.add(pmsSearchSkuInfo);
            });
            return pmsSearchSkuInfoList;
        });

        pmsSearchSkuInfos.forEach((p)->{
            System.out.println("开始==========");
            System.out.println(p.toString());
            System.out.println("结束==========");
        });
    }

    @Test
    public void listTest(){
        PmsSearchParam pmsSearchParam=new PmsSearchParam();
        pmsSearchParam.setCatalog3Id("61");
        pmsSearchParam.setKeyword("三星");
        PmsSkuAttrValue pmsSkuAttrValue=new PmsSkuAttrValue();
        pmsSkuAttrValue.setValueId("40");
        PmsSkuAttrValue pmsSkuAttrValue1=new PmsSkuAttrValue();
        pmsSkuAttrValue1.setValueId("46");
        List<PmsSkuAttrValue> pmsSkuAttrValues=new ArrayList<>();
        pmsSkuAttrValues.add(pmsSkuAttrValue);
        pmsSkuAttrValues.add(pmsSkuAttrValue1);
        //pmsSearchParam.setSkuAttrValueList(pmsSkuAttrValues);
        searchService.listTest(pmsSearchParam);
    }
}
