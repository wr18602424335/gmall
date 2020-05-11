package com.aryun.gmall.bean;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

//import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @param
 * @return
 */
@Data

public class PmsSearchParam implements Serializable {


    private String keyword;
    private String catalog3Id;
    private String[] valueId;
    //private List<PmsSkuAttrValue> skuAttrValueList;

}
