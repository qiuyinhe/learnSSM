package com.atguigu.service.impl;

import base.BaseDao;
import base.BaseService;
import base.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.UserFollowDao;
import com.atguigu.service.DictService;
import com.atguigu.service.UserFollowService;
import com.atguigu.service.UserInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.UserFollow;
import org.springframework.beans.factory.annotation.Autowired;
import vo.UserFollowVo;

import java.util.List;

@Service(interfaceClass = UserFollowService.class)
public class UserFollowServiceImpl extends BaseServiceImpl<UserFollow> implements UserFollowService {

    @Autowired
    private UserFollowDao userFollowDao;

    @Reference
    private DictService dictService;

    @Override
    protected BaseDao<UserFollow> getEntityDao() {
        return null;
    }

    @Override
    public void follow(Long userId, Long houseId) {
        UserFollow userFollow = new UserFollow();
        userFollow.setUserId(userId);
        userFollow.setHouseId(houseId);
        userFollowDao.insert(userFollow);
    }

    @Override
    public Boolean isFollowed(Long userId, Long houseId) {
        Integer count = userFollowDao.countByUserIdAndHouseId(userId, houseId);
        if(count.intValue() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public PageInfo<UserFollowVo> findListPage(int pageNum, int pageSize, Long userId) {
        PageHelper.startPage(pageNum, pageSize);
        Page<UserFollowVo> page = userFollowDao.findListPage(userId);
        List<UserFollowVo> list = page.getResult();
        for(UserFollowVo userFollowVo : list) {
            //户型：
            String houseTypeName = dictService.getNameById(userFollowVo.getHouseTypeId());
            //楼层
            String floorName = dictService.getNameById(userFollowVo.getFloorId());
            //朝向：
            String directionName = dictService.getNameById(userFollowVo.getDirectionId());
            userFollowVo.setHouseTypeName(houseTypeName);
            userFollowVo.setFloorName(floorName);
            userFollowVo.setDirectionName(directionName);
        }
        return new PageInfo<UserFollowVo>(page, 10);    }
}
