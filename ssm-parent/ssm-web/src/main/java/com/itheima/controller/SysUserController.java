package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.SysRole;
import com.itheima.domain.SysUser;
import com.itheima.service.SysRoleService;
import com.itheima.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
//@RolesAllowed("ROLE_ADMIN")
//@Secured("ROLE_ADMIN")
// PreAuthorize 使用spring el表达式
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    /**
     * 用户列表分页查询
     * @param model
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(value = "size", required = false, defaultValue = "10")int size){
        //调用业务
        PageInfo<SysUser> pageInfo =  userService.list(page,size);
        // 塞给页面
        model.addAttribute("pageInfo",pageInfo);

        return "user-list";
    }

    @RequestMapping("/initAdd")
    public String initAdd(){
        return "user-add";
    }

    @RequestMapping("/add")
    public String add(SysUser sysUser){
        // 调用业务实现添加
        int cnt = userService.add(sysUser);
        return "redirect:/user/list";
    }

    @RequestMapping("/show")
    public String show(Integer id,Model model){
        // 调用业务查询用户信息
        SysUser user = userService.getById(id);
        // 塞给页面
        model.addAttribute("user",user);
        return "user-show";
    }

    @RequestMapping("/role/initModify")
    public String initModifyRole(Integer id, Model model){
        // 获取用户信息，用户信息下有角色列表
        SysUser user = userService.getById(id);
        // 把用户返回给页面
        model.addAttribute("user",user);
        // 获取所有角色列表
        List<SysRole> roleList = roleService.list();
        // 返回给页面
        model.addAttribute("roleList",roleList);
        return "user-role-add";
    }

    @RequestMapping("/role/modify")
    public String modifyRoles(@RequestParam(value="userId") Integer userId, @RequestParam(value="ids", required = false) List<Integer> ids){
        // 调用业务进行更新
        int cnt = userService.updateRoles(userId, ids);
        return "redirect:/user/list";
    }
}
