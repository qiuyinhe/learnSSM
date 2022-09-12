package com.atguigu.controller;

import base.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.service.CommunityService;
import com.atguigu.service.DictService;
import com.github.pagehelper.PageInfo;
import entity.Community;
import entity.Dict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController {
    private final static String LIST_ACTION = "redirect:/community";

    private final static String PAGE_INDEX = "community/index";
    private final static String PAGE_SHOW = "community/show";
    private final static String PAGE_CREATE = "community/create";
    private final static String PAGE_EDIT = "community/edit";
    private final static String PAGE_SUCCESS = "common/successPage";

    @Reference
    private CommunityService communityService;

    @Reference
    private DictService dictService;

    @RequestMapping
    public String index(Map map , HttpServletRequest request){
        Map<String, Object> filters = getFilters(request);
        map.put("filters",filters);
        PageInfo<Community> pageInfo = communityService.findPage(filters);
        map.put("page",pageInfo);
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        map.put("areaList",areaList);
        return "community/index";
    };

    @RequestMapping("/create")
    public String create(ModelMap model){
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        model.addAttribute("areaList",areaList);
        return "community/create";
    };

    @RequestMapping("/save")
    public String save(Community community){
      communityService.insert(community);
      return PAGE_SUCCESS;
    };

    @RequestMapping("/edit/{id}")
    public String edit(ModelMap model, @PathVariable Long id){
        Community community = communityService.getById(id);
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        model.addAttribute("community",community);
        model.addAttribute("areaList",areaList);
        return PAGE_EDIT;
    };

    @RequestMapping("/update")
    public String update(Community community){
        communityService.update(community);
        return PAGE_SUCCESS;
    };

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable Long id){
        communityService.delete(id);
        return LIST_ACTION;
    }

}
