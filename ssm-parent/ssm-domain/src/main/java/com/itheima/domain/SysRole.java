package com.itheima.domain;

import java.util.List;
import java.util.Objects;

/**
 * 角色实体
 */
public class SysRole {

    private Integer id; // 主键
    private String roleName;// 角色名称，通过为部门名称
    private String roleDesc;// 角色描述

    private List<SysUser> users; // 拥有这个角色的所有用户信息

    private List<SysPermission> permissions; // 这个角色所拥有的权限集合

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // fetchType.Lazy SysRole会被增强 javassist技术，新的一个类型，jvs_$$SysRole
        if (o == null || getClass() != o.getClass()) return false;
        SysRole sysRole = (SysRole) o;
        return Objects.equals(id, sysRole.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<SysUser> getUsers() {
        return users;
    }

    public void setUsers(List<SysUser> users) {
        this.users = users;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }
}
