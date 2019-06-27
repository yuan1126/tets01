package com.itheima.service;

import com.itheima.domain.Product;
import com.itheima.util.PageBean;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有产品
     * @return
     */
    List<Product> getList();

    /**
     * 添加产品
     * @param product
     * @return
     */
    int add(Product product);

    /**
     * 通过编号查询产品信息
     * @param id
     * @return
     */
    Product getById(Integer id);

    /**
     * 保存修改
     * @param product
     * @return
     */
    int update(Product product);

    /**
     * 通过编号删除产品
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    PageBean<Product> pageList(int page, int size);
}
