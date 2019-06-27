package com.itheima.controller;

import com.itheima.domain.SysRole;
import com.itheima.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;

    /**
     * 角色列表
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model){
        // 调用业务查询
        List<SysRole> roles = roleService.list();
        // 塞给页面
        model.addAttribute("roles",roles);
        return "role-list";
    }
}
