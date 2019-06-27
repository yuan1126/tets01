package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.SysUserDao;
import com.itheima.domain.SysRole;
import com.itheima.domain.SysUser;
import com.itheima.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询数据库认证, 要求：用户名必须唯一
        SysUser sysUser = userDao.findByUsername(username);
        if(null != sysUser) {
            System.out.println("pwd:" + encoder.encode(sysUser.getPassword()));
            // 登陆用户的权限信息集合
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            //<security:intercept-url pattern="/**" access="ROLE_USER" />
            //获取用户所拥有的角色，进行动态授权
            for (SysRole sysRole : sysUser.getRoles()) {
                // 创建一个权限信息
                SimpleGrantedAuthority sga = new SimpleGrantedAuthority(sysRole.getRoleName());
                // 放入集合中
                authorities.add(sga);
            }

            // spring security 规范好了的登陆用户信息
            // {noop}让security不要加密
            User user = new User(sysUser.getUsername(),sysUser.getPassword(),authorities);

            return user;
        }
        return null;
    }

    @Override
    public PageInfo<SysUser> list(int page, int size) {
        // 分页插件分页
        PageHelper.startPage(page,size);
        // 查询所有
        List<SysUser> users = userDao.list();
        return new PageInfo<SysUser>(users);
    }

    @Override
    public int add(SysUser sysUser) {
        // 添加密码加密
        sysUser.setPassword(encoder.encode(sysUser.getPassword()));
        return userDao.add(sysUser);
    }

    @Override
    public SysUser getById(Integer id) {
        return userDao.getById(id);
    }

    @Override
    public int updateRoles(Integer userId, List<Integer> ids) {
        int cnt = 0;
        // 先删除 用户与角色的关系
        cnt += userDao.deleteUserRole(userId);

        // 再添加用户与角色的关系
        if(null != ids) {
            for (Integer roleId : ids) {
                // 给用户添加新角色关系
                cnt += userDao.addUserRole(userId, roleId);
            }
        }
        return cnt;
    }

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String pwd = encoder.encode("1234");
//        System.out.println(pwd);
        boolean matches = encoder.matches("1234", "$2a$10$ytH5rkaUUQyz9raTPuXZB.KJ4BcknZ.D8JUFoXD05ditpYnq8y9XG");
        System.out.println(matches);
        matches = encoder.matches("1234", "$2a$10$jV8xOsrQ1lK6luMQJ/kDRuR54OLf4VVR0f.R9F89FlD/8QflVisJi");
        System.out.println(matches);
        matches = encoder.matches("1234", "$2a$10$tu6e.2Hzl1bmPzfiHnxe7uJz5Ecv1h3n7sDwF52iMnM2ZpmQn1O.C");
        System.out.println(matches);

    }
}
