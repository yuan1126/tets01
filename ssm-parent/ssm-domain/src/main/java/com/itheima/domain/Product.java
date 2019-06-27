package com.itheima.domain;

import com.itheima.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 产品实体
 */
public class Product {

    private Integer id;//主键，自动增长
    private String productNum;//产品编号，唯一，不为空
    private String productName;//产品名称（路线名称）
    private String cityName;//出发城市
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date departureTime;//出发时间
    private Double productPrice;//产品价格
    private String productDesc;//产品描述
    private Integer productStatus;//状态（0：关闭，1：开启）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    /**
     * 处理日期的格式化显示
     * 页面调用：去掉get，把首个字母改为小写
     * @return
     */
    public String getDepartureTimeStr() {
        if(null != departureTime){
            return DateUtil.date2Str(departureTime,DateUtil.ymdHm);
        }
        return "";
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }
}
