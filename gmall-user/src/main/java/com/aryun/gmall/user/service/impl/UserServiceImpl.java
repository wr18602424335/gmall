package com.aryun.gmall.user.service.impl;

import com.aryun.gmall.user.bean.UmsMember;
import com.aryun.gmall.user.mapper.UserMapper;
import com.aryun.gmall.user.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,UmsMember> implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<UmsMember> getAllUser() {
        QueryWrapper<UmsMember> queryWrapper=new QueryWrapper<UmsMember>();
        List<UmsMember> umsMemeber = userMapper.selectList(queryWrapper);
        return umsMemeber;
    }
}
