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
public class PmsBaseAttrInfoServiceImpl extends ServiceImpl<PmsBaseAttrInfoMapper,PmsBaseAttrInfo> implements PmsBaseAttrInfoService {

    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;
    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;
    @Autowired
    PmsBaseAttrValueService pmsBaseAttrValueService;
    /**
     * 返回属性信息
     * @param id
     * @return
     */
    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String id) {
        Map<String,Object> map=Maps.newHashMap();
        map.put("catalog3_id",id);
        List<PmsBaseAttrInfo> pmsBaseAttrInfoList=pmsBaseAttrInfoMapper.selectByMap(map);
        return pmsBaseAttrInfoList;

    }
    /**
     * 添加平台属性信息(保存或者修改)
     * @param pmsBaseAttrInfo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String attrInfoList(PmsBaseAttrInfo pmsBaseAttrInfo) {
        Boolean b= this.saveOrUpdate(pmsBaseAttrInfo);
        System.out.println("返回主键ID"+pmsBaseAttrInfo.getId());
        //将获取的id插入属性值表中
        String attrInfoId= pmsBaseAttrInfo.getId();

        List<PmsBaseAttrValue> pmsBaseAttrInfoAttrValueList=pmsBaseAttrInfo.getAttrValueList();
        //将属性id赋值
        for(PmsBaseAttrValue pmsBaseAttrValue:pmsBaseAttrInfoAttrValueList){
            pmsBaseAttrValue.setAttrId(attrInfoId);
            //此处多次插入数据库会影响效率，可以用ServiceImpl中的批量插入
        }
        Boolean bb=pmsBaseAttrValueService.attrSaveValueList(pmsBaseAttrInfoAttrValueList);
        if(!(b && bb)){
            throw  new MyException("2001","保存失败");
        }

        return "true";
    }
}
