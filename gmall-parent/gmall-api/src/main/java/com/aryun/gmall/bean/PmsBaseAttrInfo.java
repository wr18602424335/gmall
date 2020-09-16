package com.aryun.gmall.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 属性值表
 * @param
 * @return
 */
@Data
public class PmsBaseAttrInfo implements Serializable {

    @TableId
    private String id;
    private String attrName;
    private String catalog3Id;
    private String isEnabled;
    @TableField(exist = false)
    List<PmsBaseAttrValue> attrValueList;
}
