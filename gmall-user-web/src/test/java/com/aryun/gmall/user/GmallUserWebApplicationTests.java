package com.aryun.gmall.user;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class GmallUserWebApplicationTests {
    @DisplayName("FASTDFS文件上传测试")
    @Test
    void contextLoads() throws IOException, MyException {
        String tracker=GmallUserWebApplicationTests.class.getResource("/tracker.conf").getPath();
        System.out.println("地址："+tracker);
        ClientGlobal.init(tracker);
        TrackerClient trackerClient=new TrackerClient();
        //获取trackerServer实列
        TrackerServer trackerServer=trackerClient.getConnection();
        //通过tarcker获取storage客户端
        StorageClient storageClient=new StorageClient(trackerServer,null);
        String[] strings=storageClient.upload_file("d:/123.jpg","jpg",null);
        for(String s:strings){
            System.out.println("输出上传内容:"+s);
        }
    }

}
