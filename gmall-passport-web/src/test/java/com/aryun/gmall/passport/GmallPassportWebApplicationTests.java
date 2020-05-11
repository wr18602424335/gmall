package com.aryun.gmall.passport;

import com.aryun.gmall.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GmallPassportWebApplicationTests {
   // @Reference
    UserService userService;
    @Test
    void contextLoads() {
        userService.getAllUser();
    }

}
