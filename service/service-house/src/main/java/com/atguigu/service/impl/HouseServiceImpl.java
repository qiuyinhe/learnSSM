package com.atguigu.service.impl;

import base.BaseDao;
import base.BaseService;
import base.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.DictDao;
import com.atguigu.dao.HouseDao;
import com.atguigu.service.DictService;
import com.atguigu.service.HouseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import util.CastUtil;
import vo.HouseQueryVo;
import vo.HouseVo;

import java.io.Serializable;


@Transactional
@Service(interfaceClass = HouseService.class)
public class HouseServiceImpl extends BaseServiceImpl<House> implements HouseService {
    @Autowired
    private HouseDao houseDao;

    @Autowired
    private DictDao dictDao;

    @Autowired
    private DictService dictService;

    @Override
    public House getById(Serializable id) {
        House house = houseDao.getById(id);
        if(null == house) return null;

        //户型：
        String houseTypeName = dictService.getNameById(house.getHouseTypeId());
        //楼层
        String floorName = dictService.getNameById(house.getFloorId());
        //建筑结构：
        String buildStructureName = dictService.getNameById(house.getBuildStructureId());
        //朝向：
        String directionName = dictService.getNameById(house.getDirectionId());
        //装修情况：
        String decorationName = dictService.getNameById(house.getDecorationId());
        //房屋用途：
        String houseUseName = dictService.getNameById(house.getHouseUseId());
        house.setHouseTypeName(houseTypeName);
        house.setFloorName(floorName);
        house.setBuildStructureName(buildStructureName);
        house.setDirectionName(directionName);
        house.setDecorationName(decorationName);
        house.setHouseUseName(houseUseName);
        return house;
    }

    @Override
    protected BaseDao<House> getEntityDao() {
        return houseDao;
    }

    @Override
    public void publish(Long id, Integer status) {
        House house = new House();
        house.setId(id);
        house.setStatus(status);
        houseDao.update(house);
    }

    @Override
    public PageInfo<HouseVo> findPageList(Integer pageNum, Integer pageSize, HouseQueryVo houseQueryVo) {
        PageHelper.startPage(pageNum, pageSize);
        Page<HouseVo> page = houseDao.findPageList(houseQueryVo);

        for (HouseVo houseVo : page) {
            String houseTypeName = dictDao.getNameById(houseVo.getHouseTypeId());
            //楼层
            String floorName = dictDao.getNameById(houseVo.getFloorId());
            //朝向：
            String directionName = dictDao.getNameById(houseVo.getDirectionId());
            houseVo.setHouseTypeName(houseTypeName);
            houseVo.setFloorName(floorName);
            houseVo.setDirectionName(directionName);
        }

        return new PageInfo<>(page,5);
    }
}
