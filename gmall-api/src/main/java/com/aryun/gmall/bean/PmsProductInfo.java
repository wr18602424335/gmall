package com.aryun.gmall.bean;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


import java.io.Serializable;
import java.util.List;

/**
 * @param
 * @return
 */
@Data
public class PmsProductInfo implements Serializable {

    @TableId
    private String id;
    @TableField("PRODUCT_NAME")
    private String spuName;

    private String description;

    private  String catalog3Id;
    @TableField(exist = false)
    private List<PmsProductSaleAttr> pmsProductSaleAttrList;
    @TableField(exist = false)
    private List<PmsProductImage> pmsProductImageList;

}


