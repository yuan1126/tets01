package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.OrdersDao;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> getList() {
        return ordersDao.getList();
    }



    @Override
    public int add(Orders orders) {
        return ordersDao.add(orders);
    }

    @Override
    public PageInfo<Orders> pageList(int page, int size) {
        // 使用pageHelper静态方法调用
        PageHelper.startPage(page,size);
        // 查询所有
        PageInfo<Orders> pageInfo = new PageInfo<Orders>(ordersDao.getList());
        return pageInfo;
    }
}
