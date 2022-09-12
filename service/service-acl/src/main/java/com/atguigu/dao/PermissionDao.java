package com.atguigu.dao;

import base.BaseDao;
import entity.Permission;

import java.util.List;

public interface PermissionDao extends BaseDao<Permission> {
    List<Permission> findAll();

    List<Permission> findListByAdminId(Long adminId);

    List<String> findAllCodeList();

    List<String> findCodeListByAdminId(Long adminId);
}
