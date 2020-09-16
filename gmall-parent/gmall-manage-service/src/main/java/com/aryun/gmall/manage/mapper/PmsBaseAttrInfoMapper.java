package com.aryun.gmall.manage.mapper;

import com.aryun.gmall.bean.PmsBaseAttrInfo;
import com.aryun.gmall.bean.PmsBaseCatalog1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface PmsBaseAttrInfoMapper extends BaseMapper<PmsBaseAttrInfo> {
    List<PmsBaseAttrInfo> getAttrValueListByValueId(@Param("str") String str);
}
