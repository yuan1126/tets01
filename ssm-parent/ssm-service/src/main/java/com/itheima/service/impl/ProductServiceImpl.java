package com.itheima.service.impl;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> getList() {
        return productDao.list();
    }

    @Override
    public int add(Product product) {
        return productDao.add(product);
    }

    @Override
    public Product getById(Integer id) {
        return productDao.getById(id);
    }

    @Override
    public int update(Product product) {
        return productDao.update(product);
    }

    @Override
    public int deleteById(Integer id) {
        return productDao.deleteById(id);
    }

    @Override
    public PageBean<Product> pageList(int page, int size) {
        PageBean<Product> pageInfo = new PageBean<Product>();
        // 页面中需要
        pageInfo.setPageCode(page);
        pageInfo.setPageSize(size);
        // 计算开始行
        int start=(page-1)*size+1;
        // 结束行
        int end = page*size;
        // 获取分页的结果集,
        List<Product> products = productDao.pageList(start,end);
        // 总记录数
        int totalCount = productDao.getCount();

        pageInfo.setBeanList(products);
        pageInfo.setTotalCount(totalCount);

        return pageInfo;
    }
}
