package com.atguigu.service;

import base.BaseService;
import com.github.pagehelper.PageInfo;
import entity.UserFollow;
import vo.UserFollowVo;

public interface UserFollowService extends BaseService<UserFollow> {

    void follow(Long userId, Long houseId);
    Boolean isFollowed(Long userId, Long houseId);
    PageInfo<UserFollowVo> findListPage(int pageNum, int pageSize, Long userId);
}
