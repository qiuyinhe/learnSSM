package com.atguigu.dao;

import base.BaseDao;
import entity.Community;

import java.util.List;

public interface CommunityDao extends BaseDao<Community> {
    List<Community> findAll();
}
