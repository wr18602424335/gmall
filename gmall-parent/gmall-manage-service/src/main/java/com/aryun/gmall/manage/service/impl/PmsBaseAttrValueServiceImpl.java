package com.aryun.gmall.manage.service.impl;

import com.aryun.gmall.bean.PmsBaseAttrInfo;
import com.aryun.gmall.bean.PmsBaseAttrValue;
import com.aryun.gmall.commonUtil.config.MyException;
import com.aryun.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.aryun.gmall.manage.mapper.PmsBaseAttrValueMapper;
import com.aryun.gmall.service.PmsBaseAttrInfoService;
import com.aryun.gmall.service.PmsBaseAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class PmsBaseAttrValueServiceImpl extends ServiceImpl<PmsBaseAttrValueMapper,PmsBaseAttrValue> implements PmsBaseAttrValueService {

    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;


    @Override
    public Boolean attrSaveValueList(List<PmsBaseAttrValue> pmsBaseAttrValues) {
        Boolean b =this.saveOrUpdateBatch(pmsBaseAttrValues);
        return b;
    }
    /**
     * 根据id查询属性信息
     * @param attrId
     * @return
     */
    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        LambdaQueryWrapper<PmsBaseAttrValue> lambdaQueryWrapper= Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(PmsBaseAttrValue::getAttrId,attrId);
        if(StringUtils.isBlank(attrId)){
            throw new MyException("2000","输入值为空");
        }
        List<PmsBaseAttrValue> pmsBaseAttrValueList= pmsBaseAttrValueMapper.selectList(lambdaQueryWrapper);
        return pmsBaseAttrValueList;
    }
}
