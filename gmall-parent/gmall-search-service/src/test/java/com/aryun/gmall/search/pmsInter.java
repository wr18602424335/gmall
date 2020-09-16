package com.aryun.gmall.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
interface pmsInter extends ElasticsearchRepository<PmsSearchSkuInfo,String> {
}
