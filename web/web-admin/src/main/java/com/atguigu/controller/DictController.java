package com.atguigu.controller;

import base.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.service.DictService;
import entity.Dict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import result.Result;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/dict")
@SuppressWarnings({"unchecked", "rawtypes"})
public class DictController extends BaseController {

    @Reference
    private DictService dictService;

    private final static String PAGE_INDEX = "dict/index";

    /**
     * 根据上级id获取子节点数据列表
     * @param id
     * @return
     */
    @GetMapping(value = "findZnodes")
    @ResponseBody
    public Result findByParentId(@RequestParam(value = "id", defaultValue = "0") Long id) {
        List<Map<String,Object>> zNodes = dictService.findZnodes(id);
        return Result.ok(zNodes);
    }

    @GetMapping
    public String index(ModelMap model) {
        return PAGE_INDEX;
    }

    /**
     * 根据上级id获取子节点数据列表
     * @param parentId
     * @return
     */
    @GetMapping(value = "findListByParentId/{parentId}")
    @ResponseBody
    public Result<List<Dict>> findListByParentId(@PathVariable Long parentId) {
        List<Dict> list = dictService.findListByParentId(parentId);
        return Result.ok(list);
    }



}