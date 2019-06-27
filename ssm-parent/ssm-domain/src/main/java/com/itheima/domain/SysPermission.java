package com.itheima.domain;

import java.util.List;

/**
 * 权限实体
 */
public class SysPermission {

    private Long id; // 主键
    private String permissionName;// 权限名称，菜单名称
    private String url;// 菜单url
    private Long pid;// 上级菜单编号

    private List<SysRole> roles; // 拥有这个权限所有角色信息

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
