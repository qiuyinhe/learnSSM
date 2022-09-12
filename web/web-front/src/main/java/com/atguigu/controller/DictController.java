package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.service.DictService;
import entity.Dict;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.Result;

import java.util.List;

@RestController
@RequestMapping("/dict")
public class DictController {

    @Reference
    private DictService dictService;

    @RequestMapping("/findListByDictCode/{dictCode}")
    public Result findListByDictCode(@PathVariable("dictCode") String dictCode){
        List<Dict> listByDictCode = dictService.findListByDictCode(dictCode);
        return Result.ok(listByDictCode);
    }

    @RequestMapping("/findListByParentId/{areaId}")
    public Result findListByParentId(@PathVariable("areaId") Long id){
        List<Dict> listByParentId = dictService.findListByParentId(id);
        return  Result.ok(listByParentId);
    }
}
