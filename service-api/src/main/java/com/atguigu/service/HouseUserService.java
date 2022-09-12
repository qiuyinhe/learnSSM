package com.atguigu.service;

import base.BaseService;
import entity.HouseUser;

import java.util.List;

public interface HouseUserService extends BaseService<HouseUser> {
    List<HouseUser> findListByHouseId(Long houseId);
}

