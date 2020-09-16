package com.aryun.gmall.manage.service.impl;




import com.aryun.gmall.bean.PmsSkuAttrValue;
import com.aryun.gmall.bean.PmsSkuSaleAttrValue;
import com.aryun.gmall.commonUtil.config.MyException;
import com.aryun.gmall.manage.mapper.PmsSkuAttrValueMapper;
import com.aryun.gmall.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.aryun.gmall.service.PmsSkuAttrValueService;
import com.aryun.gmall.service.PmsSkuSaleAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;


@Service
public class PmsSkuSaleAttrValueServiceImpl extends ServiceImpl<PmsSkuSaleAttrValueMapper, PmsSkuSaleAttrValue> implements PmsSkuSaleAttrValueService {


    @Override
    public void save(List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValues) {
        boolean b=this.saveBatch(pmsSkuSaleAttrValues);
        if(!b){
            throw new MyException("1022","保存失败");
        }
    }
}
