package com.atguigu.dao;

import base.BaseDao;
import entity.RolePermission;

import java.util.List;

public interface RolePermissionDao extends BaseDao<RolePermission> {

    void deleteByRoleId(Long roleId);

    List<Long> findPermissionIdListByRoleId(Long roleId);
}