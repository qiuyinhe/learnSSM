package com.atguigu.service;

import base.BaseService;
import entity.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService extends BaseService<Permission> {


    /**
     * 根据角色获取授权权限数据
     * @return
     */
    List<Map<String,Object>> findPermissionByRoleId(Long roleId);

    /**
     * 保存角色权限
     * @param roleId
     * @param permissionIds
     */
    void saveRolePermissionRelationShip(Long roleId, Long[] permissionIds);

    /**
     * 获取用户菜单权限
     * @param adminId
     * @return
     */
    List<Permission> findMenuPermissionByAdminId(Long adminId);
    List<Permission> findAllMenu();

    List<String> findCodeListByAdminId(Long id);
}