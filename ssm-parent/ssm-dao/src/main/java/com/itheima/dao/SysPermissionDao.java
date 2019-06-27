package com.itheima.dao;

import com.itheima.domain.SysPermission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysPermissionDao {
    /**
     * 权限列表查询
     * @return
     */
    @Select("select * from sys_permission")
    List<SysPermission> list();

    /**
     * 添加权限
     * @param permission
     * @return
     */
    @Insert("insert into sys_permission(id,permissionName,url,pid)values(sys_permission_seq.nextval,#{permissionName},#{url},#{pid})")
    int add(SysPermission permission);

    /**
     * 通过角色编号查询这个角色所拥有的权限
     */
    @Select("select * from sys_role_permission srp,sys_permission sp " +
            "where srp.permissionid=sp.id and srp.roleid=#{roleId}")
    List<SysPermission> getPermissionsByRoleId(Integer roleId);
}
