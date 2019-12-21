package com.aryun.gmall.manage;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
@DisplayName("FASTDFS文件测试")
@SpringBootTest(classes = {GmallManageWebApplication.class})
public class FastdfsTest {
    @DisplayName("FASTDFS文件上传测试")
    @Test
    void contextLoads() throws IOException, MyException {
        String tracker=FastdfsTest.class.getResource("/tracker.conf").getPath();
        //getResource方法有中文的话会获取不对需要转
        tracker = java.net.URLDecoder.decode(tracker,"utf-8");

        System.out.println("地址："+tracker);
        ClientGlobal.init(tracker);
        TrackerClient trackerClient=new TrackerClient();
        //获取trackerServer实列
        TrackerServer trackerServer=trackerClient.getConnection();
        //通过tarcker获取storage客户端
        StorageClient storageClient=new StorageClient(trackerServer,null);
        String[] strings=storageClient.upload_file("D:/123.jpg","jpg",null);
        for(String s:strings){
            System.out.println("输出上传内容:"+s);
        }
    }
}
