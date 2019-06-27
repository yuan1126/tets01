package com.itheima.domain;


import java.util.List;

/**
 * 用户实体
 */
public class SysUser {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String phoneNum;
    private int status;
    // SysRole fetchType = FetchType.LAZY, jvs_$$SysRole, 被增强
    private List<SysRole> roles; // 这个用户拥有的角色列表

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
