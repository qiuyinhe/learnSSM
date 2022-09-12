package com.atguigu.controller;

import base.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.service.UserFollowService;
import com.github.pagehelper.PageInfo;
import entity.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.Result;
import vo.UserFollowVo;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/userFollow")
public class UserFollowController extends BaseController {
    @Reference
    private UserFollowService userFollowService;
    @GetMapping("/auth/follow/{houseId}")
    public Result follow(@PathVariable("houseId") Long houseId, HttpServletRequest request){
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("USER");
        System.out.println(userInfo);
        Long userId = userInfo.getId();
        System.out.println(userId);
        System.out.println(houseId);
        userFollowService.follow(userId, houseId);
        return Result.ok();
    }

    @GetMapping(value = "/auth/list/{pageNum}/{pageSize}")
    public Result findListPage(@PathVariable Integer pageNum,
                               @PathVariable Integer pageSize,
                               HttpServletRequest request) {
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("USER");
        Long userId = userInfo.getId();
        PageInfo<UserFollowVo> pageInfo = userFollowService.findListPage(pageNum, pageSize, userId);
        return Result.ok(pageInfo);
    }
}
