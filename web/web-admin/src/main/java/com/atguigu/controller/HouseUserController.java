package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.service.HouseUserService;
import entity.HouseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/houseUser")
public class HouseUserController {

    @Reference
    private HouseUserService houseUserService;

    private final static String LIST_ACTION = "redirect:/house/";

    private final static String PAGE_CREATE = "houseUser/create";
    private final static String PAGE_EDIT = "houseUser/edit";
    private final static String PAGE_SUCCESS = "common/successPage";

    @RequestMapping("/create")
    public String create(Map map, @RequestParam Long houseId) {

        map.put("houseId", houseId);
        return PAGE_CREATE;
    }

    @PostMapping("/save")
    public String save(HouseUser houseUser) {

        houseUserService.insert(houseUser);

        return PAGE_SUCCESS;
    }


    @GetMapping("/edit/{id}")
    public String edit(ModelMap model, @PathVariable Long id) {
        HouseUser houseUser = houseUserService.getById(id);
        model.addAttribute("houseUser",houseUser);
        return PAGE_EDIT;
    }

    @PostMapping(value="/update")
    public String update(HouseUser houseUser) {

        houseUserService.update(houseUser);

        return PAGE_SUCCESS;
    }
    @GetMapping("/delete/{houseId}/{id}")
    public String delete(@PathVariable Long houseId, @PathVariable Long id) {
        houseUserService.delete(id);
        return LIST_ACTION + houseId;
    }


}
