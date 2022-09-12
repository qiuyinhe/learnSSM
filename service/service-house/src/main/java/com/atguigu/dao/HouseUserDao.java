package com.atguigu.dao;

import base.BaseDao;
import entity.HouseUser;

import java.util.List;

public interface HouseUserDao extends BaseDao<HouseUser> {
    List<HouseUser> findListByHouseId(Long houseId);
}
