package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.domain.Product;
import com.itheima.service.OrdersService;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ProductService productService;

    /**
     * 订单列表
     * 使用pageHelper分页查询
     */
    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        //调用业务查询所有订单
        PageInfo<Orders> pageInfo = ordersService.pageList(page, size);
        // 塞给页面
        model.addAttribute("pageInfo",pageInfo);
        return "order-list";
    }

    /**
     * 新增
     * @param model
     * @return
     */
    @RequestMapping("/initAdd")
    public String initAdd(Model model){
        // 获取产品列表
        List<Product> plist = productService.getList();
        model.addAttribute("plist",plist);

        return "order-add";
    }

    /**
     * 添加订单保存
     * @param orders
     * @return
     */
    @RequestMapping("/add")
    public String add(Orders orders){
        // 调用订单业务实现添加
        int cnt = ordersService.add(orders);
        return "redirect:/orders/list";
    }
}
