package com.atguigu.dao;

import base.BaseDao;
import entity.Admin;

import java.util.List;

public interface AdminDao extends BaseDao<Admin> {
    List<Admin> findAll();

    Admin getByUsername(String username);
}
