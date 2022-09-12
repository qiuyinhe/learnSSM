package com.atguigu.service;

import base.BaseService;
import entity.HouseImage;

import java.util.List;

public interface HouseImageService extends BaseService<HouseImage> {
    List<HouseImage> findList(Long houseId, Integer type);
}
