package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.service.AdminService;
import com.atguigu.service.PermissionService;
import entity.Admin;
import entity.Permission;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@SuppressWarnings({"unchecked", "rawtypes"})
public class IndexController {

    private final static String PAGE_INDEX = "frame/index";
    private final static String PAGE_MAIN = "frame/main";

    @Reference
    private AdminService adminService;

    @Reference
    private PermissionService permissionService;

    /**
     * 框架首页
     *
     * @return
     */
   /* @GetMapping("/")
    public String index() {
        return PAGE_INDEX;
    }
*/
    @GetMapping("/")
    public String index(ModelMap model) {
        //后续替换为当前登录用户id
        //后续替换为当前登录用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        Admin admin = adminService.getByUsername(user.getUsername());

        List<Permission> permissionList = permissionService.findMenuPermissionByAdminId(admin.getId());

        model.addAttribute("admin", admin);
        model.addAttribute("permissionList",permissionList);

        return PAGE_INDEX;
    }
    /**
     * 框架主页
     *
     * @return
     */
    @GetMapping("/main")
    public String main() {

        return PAGE_MAIN;
    }

    @RequestMapping("/login")
    public String login(){
        return "frame/login";
    }
}
