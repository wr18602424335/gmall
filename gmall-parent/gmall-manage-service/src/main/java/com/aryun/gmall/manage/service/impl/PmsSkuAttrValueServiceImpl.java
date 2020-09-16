package com.aryun.gmall.manage.service.impl;




import com.aryun.gmall.bean.PmsSkuAttrValue;
import com.aryun.gmall.commonUtil.config.MyException;
import com.aryun.gmall.manage.mapper.PmsSkuAttrValueMapper;
import com.aryun.gmall.service.PmsSkuAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;


@Service
public class PmsSkuAttrValueServiceImpl extends ServiceImpl<PmsSkuAttrValueMapper, PmsSkuAttrValue> implements PmsSkuAttrValueService {

    @Override
    public void save(List<PmsSkuAttrValue> pmsSkuAttrValueList) {
       boolean b= this.saveBatch(pmsSkuAttrValueList);
       if(!b){
           throw new MyException("1022","保存错误");
       }
    }
}
