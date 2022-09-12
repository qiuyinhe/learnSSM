package com.atguigu.dao;


import base.BaseDao;
import entity.Dict;


import java.util.List;

public interface DictDao extends BaseDao<Dict> {

    List<Dict> findListByParentId(Long parentId);

    Integer countIsParent(Long id);

    Dict getByDictCode(String dictCode);
    String getNameById(Long id);
}