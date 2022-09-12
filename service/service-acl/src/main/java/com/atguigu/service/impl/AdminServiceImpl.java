package com.atguigu.service.impl;

import base.BaseDao;
import base.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.AdminDao;
import com.atguigu.service.AdminService;
import entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(interfaceClass = AdminService.class)
@Transactional
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Override
    protected BaseDao<Admin> getEntityDao() {
        return adminDao;
    }

    @Override
    public List<Admin> findAll() {
        return adminDao.findAll();
    }

    @Override
    public Admin getByUsername(String username) {
        return adminDao.getByUsername(username);
    }
}
