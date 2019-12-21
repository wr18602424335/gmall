import com.aryun.gmall.bean.PmsBaseAttrInfo;
import com.aryun.gmall.manage.GmallManageServiceApplication;
import com.aryun.gmall.manage.service.impl.PmsBaseAttrInfoServiceImpl;
import com.aryun.gmall.service.PmsBaseAttrInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;


@SpringBootTest(classes = {GmallManageServiceApplication.class})
class GmallManageServiceApplicationTests {
    @Autowired
    PmsBaseAttrInfoService pmsBaseAttrInfoService;
    @DisplayName("用户登录信息")
    @Test
    void contextLoads() {
        PmsBaseAttrInfo pmsBaseAttrInfo=new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setAttrName("rui1");
        pmsBaseAttrInfo.setCatalog3Id("21");

        pmsBaseAttrInfoService.attrInfoList(pmsBaseAttrInfo);
    }

}
