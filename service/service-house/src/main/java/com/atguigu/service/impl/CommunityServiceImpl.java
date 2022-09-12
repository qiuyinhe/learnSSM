package com.atguigu.service.impl;

import base.BaseDao;
import base.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.CommunityDao;
import com.atguigu.dao.DictDao;
import com.atguigu.service.CommunityService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Community;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import util.CastUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CommunityService.class)
@Transactional
public class CommunityServiceImpl extends BaseServiceImpl<Community> implements CommunityService {

    @Autowired
    private CommunityDao communityDao;


    @Autowired
    private DictDao dictDao;

    @Override
    protected BaseDao<Community> getEntityDao() {
        return communityDao;
    }


//    重写分页的方法，给小区中 的版块和名字赋值
    @Override
    public PageInfo<Community> findPage(Map<String, Object> filters) {
        //当前页数
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 10);

        PageHelper.startPage(pageNum, pageSize);

        Page<Community> page = communityDao.findPage(filters);
        for (Community community : page) {
            String areaName = dictDao.getNameById(community.getAreaId());
            String plateName = dictDao.getNameById(community.getPlateId());
            community.setAreaName(areaName);
            community.setPlateName(plateName);
        }
        return new PageInfo<>(page,10);
    }

    @Override
    public List<Community> findAll() {
        return communityDao.findAll();
    }

    @Override
    public Community getById(Serializable id) {
        Community community = communityDao.getById(id);
        String areaName = dictDao.getNameById(community.getAreaId());
        String plateName = dictDao.getNameById(community.getPlateId());
        community.setAreaName(areaName);
        community.setPlateName(plateName);
        return community;
    }
}
