package com.atguigu.service;

import base.BaseService;
import com.github.pagehelper.PageInfo;
import entity.House;
import vo.HouseQueryVo;
import vo.HouseVo;


public interface HouseService extends BaseService<House> {
    void publish(Long id, Integer status);

    PageInfo<HouseVo> findPageList(Integer pageNum, Integer pageSize, HouseQueryVo houseQueryVo);

}
