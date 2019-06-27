package com.itheima.dao;

import com.itheima.domain.SysRole;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysRoleDao {
    /**
     * 角色列表查询
     * @return
     */
    @Select("select * from sys_role")
    List<SysRole> list();

    /**
     * 通过用户编号查询用户所拥有的角色
     * @param userId
     * @return
     */
    @Select("select * From sys_user_role sur,sys_role sr " +
            "where sur.roleid=sr.id " +
            "and sur.userid=#{userId}")
    @Results({
            @Result(property = "permissions", column = "roleid",
                    many = @Many(select="com.itheima.dao.SysPermissionDao.getPermissionsByRoleId")
            )
    })
    List<SysRole> getRolesByUserId(Integer userId);
}
