package com.aryun.gmall.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.aryun.gmall.bean.UmsMember;
import com.aryun.gmall.service.UserService;
import com.aryun.gmall.user.mapper.UserMapper;
import com.aryun.gmall.util.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UmsMember> implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisUtil redisUtil;
    @Override
    public List<UmsMember> getAllUser() {
        QueryWrapper<UmsMember> queryWrapper=new QueryWrapper<UmsMember>();
        List<UmsMember> umsMemeber = userMapper.selectAllUser();//selectList(queryWrapper);
        return umsMemeber;
    }

    /**
     * 判断是否正确
     * @param umsMember
     * @return
     */
    @Override
    public UmsMember login(UmsMember umsMember) {
        Jedis jedis= redisUtil.getJedis();
        try {
            //获取jedis连接
            if(jedis!=null){
                String userInfoStr=jedis.get("user:"+umsMember.getPassword()+":info");

                if(StringUtils.isNotBlank(userInfoStr)){
                    //密码正确
                    UmsMember umsMemberFromCache= JSON.parseObject(userInfoStr,UmsMember.class);
                    return umsMemberFromCache;
                }
            }
            //redis中没有数据，请求db
            UmsMember umsMemberFromDb=loginFromDb(umsMember);
            if(umsMemberFromDb!=null){
                //密码正确,放入缓存
                jedis.setex("user:"+umsMember.getPassword()+":info",60*60*24, JSON.toJSONString(umsMemberFromDb));
            }
            return umsMemberFromDb;
        }catch (Exception e){
            log.info("用户校验失败");
        }finally {
            jedis.close();
        }
        return null;
    }

    /**
     * 放入redis缓存
     * @param token
     * @param memberId
     */
    @Override
    public void addUserToken(String token, String memberId) {
        Jedis jedis= redisUtil.getJedis();
        jedis.setex("user:"+memberId+":info",60*60*24, JSON.toJSONString(token));
        jedis.close();
    }

    /**
     * 查询db
     * @param umsMember
     * @return
     */
    private UmsMember loginFromDb(UmsMember umsMember) {
        LambdaQueryWrapper<UmsMember> lambdaQueryWrapper= Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(UmsMember::getUsername,umsMember.getUsername()).eq(UmsMember::getPassword,umsMember.getPassword());
        UmsMember umsMember1=userMapper.selectOne(lambdaQueryWrapper);
        return umsMember1;
    }
}
