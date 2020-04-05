package com.aryun.gmall.search;

import com.aryun.gmall.service.PmsSkuService;
import io.searchbox.client.JestClient;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class GmallSearchServiceApplicationTests {
    @Reference
    PmsSkuService pmsSkuService;
    @Autowired
    JestClient jestClient;
    /**
     *
     */
    @DisplayName("向搜索引擎导入数据")
    @Test
    void data() throws IOException {
        //查询mysql数据

        //转化为es

        //导入es

        jestClient.execute(null);
    }
}
