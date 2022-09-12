package com.atguigu.controller;

import base.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.service.AdminService;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import util.QiniuUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/admin")
@SuppressWarnings({"unchecked", "rawtypes"})
public class AdminController extends BaseController {

    @Reference
    private AdminService adminService;

    @Reference
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final static String LIST_ACTION = "redirect:/admin";

    private final static String PAGE_INDEX = "admin/index";
    private final static String PAGE_CREATE = "admin/create";
    private final static String PAGE_EDIT = "admin/edit";
    private final static String PAGE_SUCCESS = "common/successPage";
    private final static String PAGE_UPLOED_SHOW = "admin/upload";
    private final static String PAGE_ASSGIN_SHOW = "admin/assignShow";
    /**
     * 列表
     */
    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        Map<String, Object> filters = getFilters(request);
        PageInfo<Admin> page = adminService.findPage(filters);

        model.addAttribute("page", page);
        model.addAttribute("filters", filters);
        return PAGE_INDEX;
    }

    /**
     * 进入新增页面
     */
    @GetMapping("/create")
    public String create() {
        return PAGE_CREATE;
    }

    /**
     * 保存新增
     */
    @PostMapping("/save")
    public String save(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        //设置默认头像
        admin.setHeadUrl("http://47.93.148.192:8080/group1/M00/03/F0/rBHu8mHqbpSAU0jVAAAgiJmKg0o148.jpg");
        adminService.insert(admin);

        return PAGE_SUCCESS;
    }

    /**
     * 进入编辑页面
     */
    @GetMapping("/edit/{id}")
    public String edit(ModelMap model, @PathVariable Long id) {
        Admin admin = adminService.getById(id);
        model.addAttribute("admin", admin);
        return PAGE_EDIT;
    }

    /**
     * 保存更新
     */
    @PostMapping(value = "/update")
    public String update(Admin admin) {

        adminService.update(admin);

        return PAGE_SUCCESS;
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminService.delete(id);
        return LIST_ACTION;
    }


    @GetMapping("/uploadShow/{id}")
    public String uploadShow(ModelMap model, @PathVariable Long id) {
        model.addAttribute("id", id);
        return PAGE_UPLOED_SHOW;
    }


    @PostMapping("/upload/{id}")
    public String upload(@PathVariable Long id, @RequestParam(value = "file") MultipartFile file) throws IOException {
        String newFileName = UUID.randomUUID().toString();
        // 上传图片
        QiniuUtils.upload2Qiniu(file.getBytes(), newFileName);
        String url = "http://rhfg61j0e.hn-bkt.clouddn.com/" + newFileName;
        Admin admin = new Admin();
        admin.setId(id);
        admin.setHeadUrl(url);
        adminService.update(admin);
        return PAGE_SUCCESS;
    }

    /**
     * 进入分配角色页面
     *
     * @param adminId
     * @return
     */
    @GetMapping("/assignShow/{adminId}")
    public String assignShow(ModelMap model, @PathVariable Long adminId) {
        Map<String, Object> roleMap = roleService.findRoleByAdminId(adminId);
        model.addAllAttributes(roleMap);
        model.addAttribute("adminId", adminId);
        return PAGE_ASSGIN_SHOW;
    }

    /**
     * 根据用户分配角色
     *
     * @param adminId
     * @param roleIds
     * @return
     */
    @PostMapping("/assignRole")
    public String assignRole(Long adminId, Long[] roleIds) {
        roleService.saveUserRoleRealtionShip(adminId, roleIds);
        return PAGE_SUCCESS;
    }
}
