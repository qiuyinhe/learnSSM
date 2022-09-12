package com.atguigu.service;

import base.BaseService;
import entity.Community;

import java.util.List;

public interface CommunityService extends BaseService<Community> {
    List<Community> findAll();
}
