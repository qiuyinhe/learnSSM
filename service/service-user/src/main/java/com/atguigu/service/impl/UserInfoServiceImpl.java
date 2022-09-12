package com.atguigu.service.impl;

import base.BaseDao;
import base.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.service.UserInfoService;

import com.atguigu.dao.UserInfoDao;

import entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(interfaceClass = UserInfoService.class)
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    protected BaseDao<UserInfo> getEntityDao() {
        return userInfoDao;
    }

    @Override
    public UserInfo getByPhone(String phone) {
        return userInfoDao.getByPhone(phone);
    }
}