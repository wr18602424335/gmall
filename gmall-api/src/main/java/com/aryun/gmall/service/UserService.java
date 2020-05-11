package com.aryun.gmall.service;


import com.aryun.gmall.bean.UmsMember;

import java.util.List;

/**
 *123
 */
public interface UserService {
    List<UmsMember> getAllUser();
    //登录用户校验
    UmsMember login(UmsMember umsMember);
    //存redis
    void addUserToken(String token, String memberId);
}
