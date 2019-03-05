package com.example.demo.global.enums;

/**
 * 订单枚举类
 * @Author: liuzy <liuzy@g2l-service.com>
 * @Date: Created in 2019/3/5 10:26
 */
public enum OrderStatusEnums {

    /**
     * 待付款
     */
    PREPARE_PAY(1),

    /**
     * 已付款
     */
    PAY(2),

    /**
     * 已退款
     */
    REFUND(3);

    OrderStatusEnums(Integer status) {
        this.status = status;
    }

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
