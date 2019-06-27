package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface ProductDao {

    /**
     * 查询所有产品信息
     * @return
     */
    @Select("select * From product")
//    @Results(
//            @Result(column = "departureTime", property = "departureTime", jdbcType = JdbcType.TIMESTAMP)
//    )
    List<Product> list();

    /**
     * 添加产品
     * @param product
     * @return
     */
    @Insert("insert into product values(PRODUCT_SEQ.NEXTVAL,#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    int add(Product product);

    /**
     * 通过编号查询产品
     * @param id
     * @return
     */
    @Select("select * from product where id=#{id}")
    Product getById(Integer id);

    /**
     * 修改产品
     * @param product
     * @return
     */
    @Update("update product set productNum = #{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where id = #{id}")
    int update(Product product);

    /**
     * 删除产品
     * @param id
     * @return
     */
    @Delete("delete from product where id=#{id}")
    int deleteById(Integer id);

    /**
     * 分页查询结果集
     * @param start
     * @param end
     * @return
     */
    @Select("select * from ( " +
            "    select rownum rn,p.* from product p where rownum<=#{end} " +
            ") t where t.rn>=${start}")
    List<Product> pageList(@Param("start") int start, @Param("end") int end);

    /**
     * 计算总记录数
     * @return
     */
    @Select("select count(1) from product")
    int getCount();
}
