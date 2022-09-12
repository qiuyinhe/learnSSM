package com.atguigu.dao;

import base.BaseDao;
import com.github.pagehelper.Page;
import entity.UserFollow;
import org.apache.ibatis.annotations.Param;
import vo.UserFollowVo;

public interface UserFollowDao extends BaseDao<UserFollow> {
    Integer countByUserIdAndHouseId(@Param("userId") Long userId, @Param("houseId") Long houseId);
    Page<UserFollowVo> findListPage(@Param("userId")Long userId);
}
