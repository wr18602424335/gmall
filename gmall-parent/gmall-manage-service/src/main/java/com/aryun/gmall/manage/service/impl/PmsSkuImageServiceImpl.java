package com.aryun.gmall.manage.service.impl;




import com.aryun.gmall.bean.PmsSkuImage;
import com.aryun.gmall.commonUtil.config.MyException;
import com.aryun.gmall.manage.mapper.PmsSkuImageMapper;
import com.aryun.gmall.service.PmsSkuImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class PmsSkuImageServiceImpl extends ServiceImpl<PmsSkuImageMapper, PmsSkuImage> implements PmsSkuImageService {

    /**
     * 保存
     * @param pmsSkuImages
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(List<PmsSkuImage> pmsSkuImages) {
        boolean b=this.saveBatch(pmsSkuImages);
        if(!b){
            throw new MyException("1022","保存失败");
        }
    }
}
