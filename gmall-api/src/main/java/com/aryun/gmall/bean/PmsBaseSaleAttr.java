package com.aryun.gmall.bean;

import com.baomidou.mybatisplus.annotation.TableId;


import java.io.Serializable;

/**
 * @param
 * @return
 */
public class PmsBaseSaleAttr implements Serializable {

    @TableId
    String id ;

    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}