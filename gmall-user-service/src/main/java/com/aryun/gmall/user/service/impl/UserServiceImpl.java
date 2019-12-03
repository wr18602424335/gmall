package com.aryun.gmall.user.service.impl;

import com.aryun.gmall.bean.UmsMember;
import com.aryun.gmall.service.UserService;
import com.aryun.gmall.user.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UmsMember> implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<UmsMember> getAllUser() {
        QueryWrapper<UmsMember> queryWrapper=new QueryWrapper<UmsMember>();
        List<UmsMember> umsMemeber = userMapper.selectAllUser();//selectList(queryWrapper);
        return umsMemeber;
    }
}
