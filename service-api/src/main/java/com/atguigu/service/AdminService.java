package com.atguigu.service;

import base.BaseService;
import entity.Admin;

import java.util.List;

public interface AdminService extends BaseService<Admin> {
    List<Admin> findAll();

    Admin getByUsername(String username);
}
