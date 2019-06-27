package com.itheima.service.impl;

import com.itheima.dao.SysRoleDao;
import com.itheima.domain.SysRole;
import com.itheima.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao roleDao;

    @Override
    public List<SysRole> list() {
        return roleDao.list();
    }
}
