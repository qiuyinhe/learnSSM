package com.atguigu.service.impl;

import base.BaseDao;
import base.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.HouseImageDao;
import com.atguigu.service.HouseImageService;
import entity.HouseImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = HouseImageService.class)
@Transactional
public class HouseImageServiceImpl extends BaseServiceImpl<HouseImage> implements HouseImageService {
    @Autowired
    private HouseImageDao houseImageDao;

    @Override
    protected BaseDao<HouseImage> getEntityDao() {
        return houseImageDao;
    }

    @Override
    public List<HouseImage> findList(Long houseId, Integer type) {
        return houseImageDao.findList(houseId,type);
    }
}
