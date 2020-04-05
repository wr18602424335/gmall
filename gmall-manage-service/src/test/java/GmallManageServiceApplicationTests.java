import com.alibaba.fastjson.JSON;
import com.aryun.gmall.bean.PmsSkuInfo;
import com.aryun.gmall.util.RedisUtil;
import com.aryun.gmall.bean.PmsBaseAttrInfo;
import com.aryun.gmall.GmallManageServiceApplication;
import com.aryun.gmall.service.PmsBaseAttrInfoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;


@SpringBootTest(classes = {GmallManageServiceApplication.class})
class GmallManageServiceApplicationTests {
    @Autowired
    PmsBaseAttrInfoService pmsBaseAttrInfoService;
    @Autowired
    RedisUtil redisUtil;
    @DisplayName("用户登录信息")
    @Test
    void contextLoads() {
        PmsBaseAttrInfo pmsBaseAttrInfo=new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setAttrName("rui1");
        pmsBaseAttrInfo.setCatalog3Id("21");

        pmsBaseAttrInfoService.attrInfoList(pmsBaseAttrInfo);
    }
    @DisplayName("redis单元测试")
    @Test
    void jedisTest() {
         Jedis jedis =redisUtil.getJedis();
        System.out.println(jedis);

        PmsSkuInfo pmsSkuInfo1= JSON.parseObject("",PmsSkuInfo.class);
        System.out.println("pmsSkuInfo1:"+pmsSkuInfo1);
    }


    public static void main(String[] args) {
        System.out.println("main:"+Thread.currentThread().getName());
        GmallManageServiceApplicationTests gmallManageServiceApplicationTests=new GmallManageServiceApplicationTests();
        gmallManageServiceApplicationTests.a();
        gmallManageServiceApplicationTests.b(0);
    }

    private  void a(){
        System.out.println("a:"+Thread.currentThread().getName());
    }
    private  void b(int i){
        if(i==3){
            return;
        }
        System.out.println("b"+i+":"+Thread.currentThread().getName());
        i++;
        b(i);
    }

}
