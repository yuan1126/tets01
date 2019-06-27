package com.itheima.service;

import com.itheima.domain.SysPermission;

import java.util.List;

public interface SysPermissionService {
    /**
     * 权限列表
     * @return
     */
    List<SysPermission> list();

    /**
     * 添加权限菜单
     * @param permission
     * @return
     */
    int add(SysPermission permission);
}
