package org.cplego.foodie.pojo.vo;

import org.cplego.foodie.pojo.bo.OrderBO;

public class OrderVO {
    private OrderBO orderBO;
    private String merchantOrderId;         // 商户订单号
    private Integer amount;                 // 实际支付总金额（包含商户所支付的订单费邮费总额）
    private String returnUrl;               // 支付成功后的回调地址（学生自定义）

    public OrderBO getOrderBO() {
        return orderBO;
    }

    public void setOrderBO(OrderBO orderBO) {
        this.orderBO = orderBO;
    }

    public String getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(String merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
