package com.itheima.controller;

import com.itheima.domain.SysPermission;
import com.itheima.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService permissionService;

    @RequestMapping("/list")
    public String list(Model model){
        // 调用业务查询
        List<SysPermission> permissionList = permissionService.list();
        // 塞给页面
        model.addAttribute("permissionList",permissionList);
        return "permission-list";
    }

    @RequestMapping("/initAdd")
    public String initAdd(){
        return "permission-add";
    }

    @RequestMapping("/add")
    public String add(SysPermission permission){
        // 调用业务实现添加
        int cnt = permissionService.add(permission);
        return "redirect:/permission/list";
    }
}
