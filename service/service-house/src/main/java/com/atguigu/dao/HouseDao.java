package com.atguigu.dao;

import base.BaseDao;
import com.github.pagehelper.Page;
import entity.House;
import org.apache.ibatis.annotations.Param;
import vo.HouseQueryVo;
import vo.HouseVo;

public interface HouseDao extends BaseDao<House> {
    void publish(Long id, Integer status);

    Page<HouseVo> findPageList(@Param("vo") HouseQueryVo houseQueryVo);
}
