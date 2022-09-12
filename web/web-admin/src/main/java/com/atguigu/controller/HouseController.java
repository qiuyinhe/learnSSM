package com.atguigu.controller;

import base.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.service.*;
import com.github.pagehelper.PageInfo;
import entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/house")
public class HouseController extends BaseController {
    @Reference
    private HouseService houseService;
    @Reference
    private CommunityService communityService;
    @Reference
    private DictService dictService;

    @Reference
    private HouseImageService houseImageService;

    @Reference
    private HouseBrokerService houseBrokerService;

    @Reference
    private HouseUserService houseUserService;

    private final static String LIST_ACTION = "redirect:/house";
    private final static String PAGE_INDEX = "house/index";
    private final static String PAGE_SHOW = "house/show";
    private final static String PAGE_CREATE = "house/create";
    private final static String PAGE_EDIT = "house/edit";
    private final static String PAGE_SUCCESS = "common/successPage";

    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        Map<String,Object> filters = getFilters(request);
        PageInfo<House> page = houseService.findPage(filters);

        model.addAttribute("page", page);
        model.addAttribute("filters", filters);

        model.addAttribute("communityList",communityService.findAll());
        model.addAttribute("houseTypeList",dictService.findListByDictCode("houseType"));
        model.addAttribute("floorList",dictService.findListByDictCode("floor"));
        model.addAttribute("buildStructureList",dictService.findListByDictCode("buildStructure"));
        model.addAttribute("directionList",dictService.findListByDictCode("direction"));
        model.addAttribute("decorationList",dictService.findListByDictCode("decoration"));
        model.addAttribute("houseUseList",dictService.findListByDictCode("houseUse"));
        return PAGE_INDEX;
    }

    @RequestMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute("communityList",communityService.findAll());
        model.addAttribute("houseTypeList",dictService.findListByDictCode("houseType"));
        model.addAttribute("floorList",dictService.findListByDictCode("floor"));
        model.addAttribute("buildStructureList",dictService.findListByDictCode("buildStructure"));
        model.addAttribute("directionList",dictService.findListByDictCode("direction"));
        model.addAttribute("decorationList",dictService.findListByDictCode("decoration"));
        model.addAttribute("houseUseList",dictService.findListByDictCode("houseUse"));
//        model.addAttribute("house",house);
        return PAGE_CREATE;
    }

    @PostMapping("/save")
    public String save(House house) {
        houseService.insert(house);

        return PAGE_SUCCESS;
    }

    @GetMapping("/edit/{id}")
    public String edit(ModelMap model,@PathVariable Long id) {
        House house = houseService.getById(id);
        model.addAttribute("house",house);

        model.addAttribute("communityList",communityService.findAll());
        model.addAttribute("houseTypeList",dictService.findListByDictCode("houseType"));
        model.addAttribute("floorList",dictService.findListByDictCode("floor"));
        model.addAttribute("buildStructureList",dictService.findListByDictCode("buildStructure"));
        model.addAttribute("directionList",dictService.findListByDictCode("direction"));
        model.addAttribute("decorationList",dictService.findListByDictCode("decoration"));
        model.addAttribute("houseUseList",dictService.findListByDictCode("houseUse"));
        return PAGE_EDIT;
    }


    @PostMapping(value="/update")
    public String update(House house) {

        houseService.update(house);

        return PAGE_SUCCESS;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        houseService.delete(id);
        return LIST_ACTION;
    }



    @GetMapping("/publish/{id}/{status}")
    public String publish(@PathVariable Long id,@PathVariable Integer status){
        houseService.publish(id,status);

        return LIST_ACTION;
    }

    @GetMapping("/{houseId}")
    public String show( @PathVariable Long houseId,Map map){
        House house = houseService.getById(houseId);
        map.put("house",house);
        Community community = communityService.getById(house.getCommunityId());
        map.put("community",community);

        List<HouseImage> houseImage1List = houseImageService.findList(houseId, 1);
        List<HouseImage> houseImage2List = houseImageService.findList(houseId, 2);

        List<HouseBroker> houseBrokerList = houseBrokerService.findListByHouseId(houseId);
        List<HouseUser> houseUserList = houseUserService.findListByHouseId(houseId);
        map.put("houseBrokerList",houseBrokerList);
        map.put("houseUserList",houseUserList);
        map.put("houseImage1List",houseImage1List);
        map.put("houseImage2List",houseImage2List);

        return PAGE_SHOW;
    }
}
