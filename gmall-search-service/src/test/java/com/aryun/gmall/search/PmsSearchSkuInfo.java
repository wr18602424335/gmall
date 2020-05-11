package com.aryun.gmall.search;

import com.aryun.gmall.bean.PmsSkuAttrValue;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
@Data
@Document(indexName = "gmallpms",type = "PmsSkuInfo")
public class PmsSearchSkuInfo implements Serializable {
    String id;
    private String skuName;
    private String skuDesc;
    private String catalog3Id;
    private BigDecimal price;
    private String skuDefaultImg;
    private double hotScore;
    private String productId;
    private List<PmsSkuAttrValue> skuAttrValueList;
}
