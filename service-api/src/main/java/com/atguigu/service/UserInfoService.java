package com.atguigu.service;


import base.BaseService;
import entity.UserInfo;

public interface UserInfoService extends BaseService<UserInfo> {
    UserInfo getByPhone(String phone);
}