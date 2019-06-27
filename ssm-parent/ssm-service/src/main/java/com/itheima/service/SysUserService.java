package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface SysUserService extends UserDetailsService {
    /**
     * 用户列表分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<SysUser> list(int page, int size);

    /**
     * 添加用户
     * @param sysUser
     * @return
     */
    int add(SysUser sysUser);

    /**
     * 通过编号查询用户信息
     * @param id
     * @return
     */
    SysUser getById(Integer id);

    /**
     * 更新用户的角色信息
     * @param userId
     * @param ids
     * @return
     */
    int updateRoles(Integer userId, List<Integer> ids);
}
