package com.aryun.gmall.user;


import com.aryun.gmall.GmallUserServiceApplication;
import com.aryun.gmall.bean.UmsMember;
import com.aryun.gmall.user.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@SpringBootTest(classes = {GmallUserServiceApplication.class})
class GmallUserServiceApplicationTests {

    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Test
    void contextLoads() {
        //得到会话sqlSession
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //创建代理对象
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        //调用mapper的方法,返回映射在pojo 中Orders(里面补充了User user)
        List<UmsMember>  list=userMapper.selectAllUser();
        System.out.println(list);
        sqlSession.close();
    }
    @Test
    public void getNumberValue(){
        Object b=0;
        Double number=Double.valueOf(String.valueOf(b));
        BigDecimal bigDecimal=BigDecimal.valueOf(number);
        BigDecimal bb=bigDecimal.setScale(2, RoundingMode.HALF_UP);
        System.out.println(bb);
        String n=null;
        String.valueOf(n);
        System.out.println( String.valueOf(n));
        System.out.println(StringUtils.isBlank(String.valueOf(n)));
    }


    public static void main(String[] args) {
        int[] i=null;
        int i1=i.length;
        System.out.println(i1);
    }
}
