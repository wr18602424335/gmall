package com.aryun.gmall.user.mapper;

import com.aryun.gmall.bean.UmsMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper  extends BaseMapper<UmsMember> {
    List<UmsMember> selectAllUser();
}
