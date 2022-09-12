package com.atguigu.service;

import base.BaseService;
import entity.HouseBroker;

import java.util.List;

public interface HouseBrokerService extends BaseService<HouseBroker> {
    List<HouseBroker> findListByHouseId(Long houseId);
}
