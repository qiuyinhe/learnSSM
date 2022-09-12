package com.atguigu.service.impl;

import base.BaseDao;
import base.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.HouseDao;
import com.atguigu.dao.HouseUserDao;
import com.atguigu.service.HouseUserService;
import entity.HouseUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = HouseUserService.class)
public class HouseUserServiceImpl extends BaseServiceImpl<HouseUser> implements HouseUserService {
    @Autowired
    private HouseUserDao houseUserDao;

    @Override
    protected BaseDao<HouseUser> getEntityDao() {
        return houseUserDao;
    }

    @Override
    public List<HouseUser> findListByHouseId(Long houseId) {
        return houseUserDao.findListByHouseId(houseId);
    }
}
