package com.atguigu.dao;

import base.BaseDao;
import entity.AdminRole;

import java.util.List;

public interface AdminRoleDao extends BaseDao<AdminRole> {

    void deleteByAdminId(Long adminId);

    List<Long> findRoleIdByAdminId(Long adminId);
}