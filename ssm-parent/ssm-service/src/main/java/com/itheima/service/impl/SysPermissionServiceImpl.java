package com.itheima.service.impl;

import com.itheima.dao.SysPermissionDao;
import com.itheima.domain.SysPermission;
import com.itheima.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionDao permissionDao;
    @Override
    public List<SysPermission> list() {
        return permissionDao.list();
    }

    @Override
    public int add(SysPermission permission) {
        return permissionDao.add(permission);
    }
}
