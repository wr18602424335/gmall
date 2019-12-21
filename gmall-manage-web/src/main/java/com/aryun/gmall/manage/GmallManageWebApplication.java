package com.aryun.gmall.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,scanBasePackages={"com.aryun.gmall"})
public class GmallManageWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallManageWebApplication.class, args);
    }

}
