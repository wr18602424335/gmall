package com.aryun.gmall.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.aryun.gmall")
public class GmallSearchServiceApplication {

    public static void main(String[] args) {
        //System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(GmallSearchServiceApplication.class, args);
    }

}
