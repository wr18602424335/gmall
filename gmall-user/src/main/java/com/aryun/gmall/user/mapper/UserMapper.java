package com.aryun.gmall.user.mapper;

import com.aryun.gmall.user.bean.UmsMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
@Repository
public interface UserMapper  extends BaseMapper<UmsMember> {
   // List<UmsMember> selectAllUser();
}
