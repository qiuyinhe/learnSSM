package com.atguigu.dao;

import base.BaseDao;
import entity.HouseBroker;

import java.util.List;

public interface HouseBrokerDao extends BaseDao<HouseBroker> {
    List<HouseBroker> findListByHouseId(Long houseId);
}
