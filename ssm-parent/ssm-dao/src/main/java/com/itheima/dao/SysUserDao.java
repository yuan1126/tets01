package com.itheima.dao;

import com.itheima.domain.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface SysUserDao {

    /**
     * 通过用户名称查询用户信息
     * 认证
     * @param username
     * @return
     */
    @Select("select * from sys_user where username=#{username}")
    @ResultMap("myResults")
    SysUser findByUsername(String username);

    /**
     * 查询所有
     * @return
     */
    @Select("select * From sys_user")
    List<SysUser> list();

    /**
     * 添加用户
     * @param sysUser
     * @return
     */
    @Insert("insert into sys_user(id,username,email,password,phonenum,status)values(user_seq.nextval,#{username},#{email},#{password},#{phoneNum},#{status})")
    int add(SysUser sysUser);

    /**
     * 通过用户编号获取用户信息
     * @param id
     * @return
     */
    @Select("select * from sys_user where id=#{id}")
    @Results(id="myResults",value={
            @Result(id = true, property = "id", column = "id"),
            @Result( property = "roles", column = "id",
                    // 通过用户编号查询角色信息
                    many = @Many(select = "com.itheima.dao.SysRoleDao.getRolesByUserId")
            )
    })
    SysUser getById(Integer id);

    /**
     * 删除用户与角色的关系
     * @param userId
     * @return
     */
    @Delete("delete from sys_user_role where userid=#{userId}")
    int deleteUserRole(Integer userId);

    /**
     * 添加用户与角色的关系
     * @param userId
     * @param roleId
     * @return
     */
    @Insert("insert into sys_user_role values(#{userId},#{roleId})")
    int addUserRole(@Param("userId") Integer userId, @Param("roleId")Integer roleId);
}
