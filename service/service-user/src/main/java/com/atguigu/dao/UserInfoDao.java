package com.atguigu.dao;


import base.BaseDao;
import entity.UserInfo;

public interface UserInfoDao extends BaseDao<UserInfo> {
    UserInfo getByPhone(String phone);
}