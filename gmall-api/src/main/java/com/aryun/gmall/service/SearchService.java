package com.aryun.gmall.service;

import com.aryun.gmall.bean.PmsSearchParam;
import com.aryun.gmall.bean.PmsSearchSkuInfo;

import java.util.List;

public interface SearchService {
    List<PmsSearchSkuInfo> list(PmsSearchParam pmsSearchParam);
    List<PmsSearchSkuInfo> listTest(PmsSearchParam pmsSearchParam);
}
