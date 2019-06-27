package com.itheima.controller;

import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询所有产品列表
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String productList(Model model){
        // 调用业务查询所有产品
        List<Product> products = productService.getList();
        // 塞回给页面
        model.addAttribute("products",products);
        return "product-list";
    }

    /**
     * 查询所有产品列表
     * @param model
     * @return
     */
    @RequestMapping("/pageList")
    public String pageList(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(value = "size", required = false, defaultValue = "10")int size){
        // 调用业务查询分页查询产品
        PageBean<Product> pageInfo = productService.pageList(page,size);
        // 塞回给页面
        model.addAttribute("pageInfo",pageInfo);

        // 获取登陆用户名
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        // 获取登陆用户信息
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getUsername());
        return "product-list";
    }

    /**
     * 添加产品页面
     * @return
     */
    @RequestMapping("/initAdd")
    public String initAdd(){
        return "product-add";
    }

    /**
     * 添加产品保存
     * @param product
     * @return
     */
    @RequestMapping("/add")
    public String add(Product product){
        // 调用业务实现添加
        int cnt = productService.add(product);
        // 重定向到产品列表
        return "redirect:/product/list";
    }

    /**
     * 修改数据回显
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/initUpdate")
    public String intiUpdate(Integer id, Model model){
        // 通过编号查询产品
        Product product = productService.getById(id);
        // 塞给页面
        model.addAttribute("product",product);
        // 跳转到修改页面
        return "product-update";
    }

    /**
     * 保存修改
     * @param product
     * @return
     */
    @RequestMapping("/update")
    public String update(Product product){
        //调用业务实现保存
        int cnt = productService.update(product);
        // 跳转到产品列表
        return "redirect:/product/list";
    }

    /**
     * 删除产品
     */
    @RequestMapping("/delete")
    public String delete(Integer id){
        // 调用业务进行删除
        int cnt = productService.deleteById(id);
        // 成功跳转到产品列表
        return "redirect:/product/list";
    }
}
