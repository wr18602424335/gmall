package com.aryun.gmall.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class PmsProductSaleAttr implements Serializable {

    @TableId
    String id ;

    String productId;

    String saleAttrId;

    String saleAttrName;

    @TableField(exist = false)
    List<PmsProductSaleAttrValue> spuSaleAttrValueList;
}
