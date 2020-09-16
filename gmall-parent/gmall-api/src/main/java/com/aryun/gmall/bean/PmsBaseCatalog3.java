package com.aryun.gmall.bean;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @param
 * @return
 */
@Slf4j
@Data
public class PmsBaseCatalog3 implements Serializable {
    @TableId
    private String id;
    private String name;
    private String catalog2Id;
}
