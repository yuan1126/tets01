package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;

import java.util.List;

public interface OrdersService {
    /**
     * 订单列表
     * @return
     */
    List<Orders> getList();

    /**
     * 新增订单
     * @param orders
     * @return
     */
    int add(Orders orders);

    /**
     * 使用pageHelper分页
     * @param page
     * @param size
     * @return
     */
    PageInfo<Orders> pageList(int page, int size);
}
