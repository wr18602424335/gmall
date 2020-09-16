package com.aryun.gmall.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GmallUserApplicationTests {

    @Test
    void contextLoads() {
        A aa=new A();
        aa.a="2";
        A aa1=new A();
        System.out.println(aa1.a);
    }

}
