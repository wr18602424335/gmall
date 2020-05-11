package com.aryun.gmall.manage.service.impl;


import com.alibaba.fastjson.JSON;
import com.aryun.gmall.bean.*;
import com.aryun.gmall.commonUtil.config.MyException;
import com.aryun.gmall.manage.mapper.*;
import com.aryun.gmall.service.PmsSkuAttrValueService;
import com.aryun.gmall.service.PmsSkuImageService;
import com.aryun.gmall.service.PmsSkuSaleAttrValueService;
import com.aryun.gmall.service.PmsSkuService;
import com.aryun.gmall.util.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Service
public class PmsSkuServiceImpl extends ServiceImpl<PmsSkuInfoMapper, PmsSkuInfo> implements PmsSkuService {
    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;
    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;
    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;
    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;
    @Autowired
    PmsSkuAttrValueService pmsSkuAttrValueService;
    @Autowired
    PmsSkuImageService pmsSkuImageService;
    @Autowired
    PmsSkuSaleAttrValueService pmsSkuSaleAttrValueService;
    @Autowired
    PmsProductImageMapper pmsProductImageMapper;
    @Autowired
    RedisUtil redisUtil;
    /**
     * 保存sku操作
     * @param pmsSkuInfo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        //保存sku属性信息表数据
        int b=pmsSkuInfoMapper.insert(pmsSkuInfo);
        if(b==0){
            throw new MyException("1022","新增失败");
        }
        //sku属性
        String skuId=pmsSkuInfo.getId();
        //spu属性
        String spuId=pmsSkuInfo.getProductId();

        //保存pmsSkuAttrValue数据
        List<PmsSkuAttrValue> pmsSkuAttrValues=pmsSkuInfo.getSkuAttrValueList();
        for(PmsSkuAttrValue pmsSkuAttrValue:pmsSkuAttrValues){
            pmsSkuAttrValue.setSkuId(skuId);
        }
        pmsSkuAttrValueService.save(pmsSkuAttrValues);
        //保存skuImageList图片数据
        List<PmsSkuImage> pmsSkuImages= pmsSkuInfo.getSkuImageList();
        for(PmsSkuImage pmsSkuImage:pmsSkuImages){
            //获取spuImage的ID
            String spuImage=pmsSkuImage.getImgName();
            QueryWrapper<PmsProductImage> queryWrapper= Wrappers.query();
            queryWrapper.eq("product_id",spuId).eq("img_name",spuImage);
            PmsProductImage pmsProductImage=pmsProductImageMapper.selectOne(queryWrapper);
            String spuImageId=pmsProductImage.getId();
            pmsSkuImage.setProductImgId(spuImageId);
            pmsSkuImage.setSkuId(skuId);
        }
        pmsSkuImageService.save(pmsSkuImages);
        //保存pmsSkuAttrValue 属性值保存
        List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValues=pmsSkuInfo.getSkuSaleAttrValueList();
        for(PmsSkuSaleAttrValue pmsSkuSaleAttrValue:pmsSkuSaleAttrValues){
            pmsSkuSaleAttrValue.setSkuId(skuId);
        }
        pmsSkuSaleAttrValueService.save(pmsSkuSaleAttrValues);
    }


    /**
     * 页面载入后传入前端页面的产品id下的sku描述信息
     * @param productId
     * @return
     */
    @Override
    public List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId) {
        List<PmsSkuInfo>  pmsSkuInfoList=pmsSkuInfoMapper.getSkuSaleAttrValueListBySpu(productId);
        return pmsSkuInfoList;
    }

    /**
     * 缓存，存查设计到分布式redis锁了，重点
     * @param skuId
     * @return
     */
    @Override
    public PmsSkuInfo getSkuById(String skuId) {
        System.out.println("同学："+Thread.currentThread().getName()+"进入方法");
        String skuIdKey="skuId"+skuId+"info";
        String lockKey="sku"+skuId+"lock";
        String tokenUuId= UUID.randomUUID().toString();
        //连接缓存
        Jedis jedis=redisUtil.getJedis();
        //查询缓存
        String pmsSkuInfo=jedis.get(skuIdKey);
        if(StringUtils.isEmpty(pmsSkuInfo)){
            //如果是空就查询数据库，然后在存入redis(防止缓存击穿，问题，设置锁)
            //先查询锁是否存在，就是看是否能获取到锁
            String skuIdLock=jedis.set(lockKey,tokenUuId,"nx","px",10*1000);
            //如果有锁，进入自旋
            System.out.println("skuIdLock："+Thread.currentThread().getName()+skuIdLock);
            if(StringUtils.isEmpty(skuIdLock) ||  !StringUtils.equals("OK",skuIdLock)){
                System.out.println("同学："+Thread.currentThread().getName()+"进入休眠");
                try{
                    Thread.sleep(1000*5);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                    }
                return getSkuById(skuId);
                }
            //加锁10秒钟(获取一个随机的token,用来校验是否为自己的锁
            System.out.println("同学："+Thread.currentThread().getName()+"上锁取数据前");
            //jedis.set(lockKey,tokenUuId,"nx","px",10*1000);
            System.out.println("同学："+Thread.currentThread().getName()+"上锁取数据后："+ jedis.get(lockKey));
            PmsSkuInfo pmsSkuInfoDb=getSkuBuIdFromDb(skuId);
            //取完数据等3秒，测试用
            try {
                Thread.sleep(1000*3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //缓存穿透问题
            if(pmsSkuInfoDb!=null){
                String pmsSkuInfoDbStr= JSON.toJSONString(pmsSkuInfoDb);
                //存
                jedis.set(skuIdKey,pmsSkuInfoDbStr);
            }else{
                //防止缓存穿透设置空值
                jedis.setex(skuIdKey,60*2,"");
            }
            //两种方法，下面先使用lua脚本方式,判断后直接删除
            String script="if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
            //比较当前锁是否和之前获取的锁一致
            System.out.println("同学："+Thread.currentThread().getName()+"解锁返回数据");
            jedis.eval(script, Collections.singletonList(lockKey),Collections.singletonList(tokenUuId));
            //删除锁(先判断token是否相同)
            //jedis.del(skuIdLock);
            //关闭缓存
            jedis.close();
            }
        //缓存取值返回前端
        String pmsSkuInfoRedis= jedis.get(skuIdKey);
        PmsSkuInfo pmsSkuInfo1= JSON.parseObject(pmsSkuInfoRedis,PmsSkuInfo.class);
        return pmsSkuInfo1;
    }


    /**
     * 获取
     * @param skuId
     * @return
     */
    private PmsSkuInfo  getSkuBuIdFromDb(String skuId){
        //商品信息
        PmsSkuInfo pmsSkuInfo= pmsSkuInfoMapper.selectById(skuId);
        //图片列表
        LambdaQueryWrapper<PmsSkuImage> lambdaQueryWrapper= Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(PmsSkuImage::getSkuId,skuId);
        List<PmsSkuImage> pmsSkuImageList=pmsSkuImageMapper.selectList(lambdaQueryWrapper);
        pmsSkuInfo.setSkuImageList(pmsSkuImageList);
        return pmsSkuInfo;
    }

    /**
     *
     * @param catalog3Id
     * @return
     */
    @Override
    public List<PmsSkuInfo> getAllSku(String catalog3Id) {
        LambdaQueryWrapper lambdaQueryWrapper=Wrappers.lambdaQuery();
        List<PmsSkuInfo> pmsSkuInfos=pmsSkuInfoMapper.selectList(lambdaQueryWrapper);
        for(PmsSkuInfo pmsSkuInfo:pmsSkuInfos){
            String skuId=pmsSkuInfo.getId();
            LambdaQueryWrapper<PmsSkuAttrValue> lambdaQueryWrapper1=Wrappers.lambdaQuery();
            lambdaQueryWrapper1.eq(PmsSkuAttrValue::getSkuId,skuId);
            List<PmsSkuAttrValue> pmsSkuAttrValueList=pmsSkuAttrValueMapper.selectList(lambdaQueryWrapper1);
            pmsSkuInfo.setSkuAttrValueList(pmsSkuAttrValueList);
        }
        return pmsSkuInfos;
    }
}
