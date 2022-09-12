package com.atguigu.service.impl;

import base.BaseDao;
import base.BaseService;
import base.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.HouseBrokerDao;
import com.atguigu.service.HouseBrokerService;
import entity.HouseBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = HouseBrokerService.class)
@Transactional
public class HouseBrokerServiceImpl extends BaseServiceImpl<HouseBroker> implements HouseBrokerService {

    @Autowired
    private HouseBrokerDao houseBrokerDao;

    @Override
    protected BaseDao<HouseBroker> getEntityDao() {
        return houseBrokerDao;
    }

    @Override
    public List<HouseBroker> findListByHouseId(Long houseId) {
        return houseBrokerDao.findListByHouseId(houseId);
    }
}
