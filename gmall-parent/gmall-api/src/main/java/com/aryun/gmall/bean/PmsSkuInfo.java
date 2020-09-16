package com.aryun.gmall.bean;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @param
 * @return
 */
@Data
public class PmsSkuInfo implements Serializable {

    @TableId
    String id;

    String productId;

    BigDecimal price;

    String skuName;

    BigDecimal weight;

    String skuDesc;

    String catalog3Id;

    String skuDefaultImg;
    @TableField(exist = false)
    String spuId;
    @TableField(exist = false)
    List<PmsSkuImage> skuImageList;
    @TableField(exist = false)
    List<PmsSkuAttrValue> skuAttrValueList;
    @TableField(exist = false)
    List<PmsSkuSaleAttrValue> skuSaleAttrValueList;

}
