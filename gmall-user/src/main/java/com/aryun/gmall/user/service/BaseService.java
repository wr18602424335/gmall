package com.aryun.gmall.user.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class BaseService implements IService {
    @Override
    public boolean save(Object entity) {
        return false;
    }

    @Override
    public boolean saveBatch(Collection entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean removeById(Serializable id) {
        return false;
    }

    @Override
    public boolean remove(Wrapper queryWrapper) {
        return false;
    }

    @Override
    public boolean updateById(Object entity) {
        return false;
    }

    @Override
    public boolean update(Object entity, Wrapper updateWrapper) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Object entity) {
        return false;
    }

    @Override
    public Object getById(Serializable id) {
        return null;
    }

    @Override
    public Object getOne(Wrapper queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper queryWrapper) {
        return null;
    }

    @Override
    public int count(Wrapper queryWrapper) {
        return 0;
    }

    @Override
    public List list(Wrapper queryWrapper) {
        return null;
    }

    @Override
    public IPage page(IPage page, Wrapper queryWrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listMaps(Wrapper queryWrapper) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> pageMaps(IPage page, Wrapper queryWrapper) {
        return null;
    }

    @Override
    public BaseMapper getBaseMapper() {
        return null;
    }

    @Override
    public List listObjs(Wrapper queryWrapper, Function mapper) {
        return null;
    }

    @Override
    public Object getObj(Wrapper queryWrapper, Function mapper) {
        return null;
    }

    @Override
    public Collection listByMap(Map columnMap) {
        return null;
    }

    @Override
    public Collection listByIds(Collection idList) {
        return null;
    }

    @Override
    public boolean removeByIds(Collection idList) {
        return false;
    }

    @Override
    public boolean removeByMap(Map columnMap) {
        return false;
    }
}
