package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.service.*;
import com.github.pagehelper.PageInfo;
import entity.*;
import org.springframework.web.bind.annotation.*;
import result.Result;
import vo.HouseQueryVo;
import vo.HouseVo;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/house")
public class HouseController {
    @Reference
    private HouseService houseService;


    @Reference
    private CommunityService communityService;

    @Reference
    private HouseBrokerService houseBrokerService;

    @Reference
    private HouseImageService houseImageService;

    @Reference
    private UserFollowService userFollowService;

    @RequestMapping("/list/{pageNum}/{pageSize}")
    public Result findPageList(@PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize,
                               @RequestBody HouseQueryVo houseQueryVo) {

        PageInfo<HouseVo> pageList = houseService.findPageList(pageNum, pageSize, houseQueryVo);
        return Result.ok(pageList);
    }

    @GetMapping("info/{id}")
    public Result info(@PathVariable Long id, HttpServletRequest request) {
        House house = houseService.getById(id);
        Community community = communityService.getById(house.getCommunityId());
        List<HouseBroker> houseBrokerList = houseBrokerService.findListByHouseId(id);
        List<HouseImage> houseImage1List = houseImageService.findList(id, 1);

        Map<String, Object> map = new HashMap<>();
        map.put("house",house);
        map.put("community",community);
        map.put("houseBrokerList",houseBrokerList);
        map.put("houseImage1List",houseImage1List);

        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("USER");
        Boolean isFollow = false;
        if(null != userInfo) {
            Long userId = userInfo.getId();
            isFollow = userFollowService.isFollowed(userId, id);
        }
        map.put("isFollow",isFollow);
        return Result.ok(map);
    }
}
