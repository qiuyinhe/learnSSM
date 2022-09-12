package com.atguigu.controller;


import base.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.service.PermissionService;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import entity.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

    @Reference
    private RoleService roleService;

    @Reference
    private PermissionService permissionService;

    private final static String LIST_ACTION = "redirect:/role";
    private final static String PAGE_INDEX = "role/index";
    private final static String PAGE_CREATE = "role/create";
    private final static String PAGE_EDIT = "role/edit";
    private final static String PAGE_SUCCESS = "common/successPage";
    private final static String PAGE_ASSGIN_SHOW = "role/assignShow";

    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        Map<String, Object> filters = getFilters(request);
        PageInfo<Role> page = roleService.findPage(filters);

        model.addAttribute("page", page);
        model.addAttribute("filters", filters);
        return PAGE_INDEX;
    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        return PAGE_CREATE;
    }

    @PostMapping("/save")
    public String save(Role role) {
        roleService.insert(role);
        return PAGE_SUCCESS;
    }

    @GetMapping("/edit/{id}")
    public String edit(ModelMap model, @PathVariable Long id) {
        Role role = roleService.getById(id);
        model.addAttribute("role", role);
        return PAGE_EDIT;
    }

    @PostMapping(value = "/update")
    public String update(Role role) {

        roleService.update(role);
        return PAGE_SUCCESS;
    }

    @PreAuthorize("hasAuthority('role.delete')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        return LIST_ACTION;
    }


    @GetMapping("/assignShow/{roleId}")
    public String assignShow(ModelMap model,@PathVariable Long roleId) {
        List<Map<String,Object>> zNodes = permissionService.findPermissionByRoleId(roleId);
        model.addAttribute("zNodes", zNodes);
        model.addAttribute("roleId", roleId);
        return PAGE_ASSGIN_SHOW;
    }

    @PostMapping("/assignPermission")
    public String assignPermission(Long roleId,Long[] permissionIds) {
        permissionService.saveRolePermissionRelationShip(roleId, permissionIds);
        return PAGE_SUCCESS;
    }

}