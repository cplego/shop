package org.cplego.foodie.service;

import org.cplego.foodie.pojo.OrderStatus;
import org.cplego.foodie.pojo.bo.OrderBO;
import org.cplego.foodie.pojo.vo.OrderVO;
import org.cplego.foodie.utils.JSONResult;

public interface OrderService {
    /**
     * 创建订单
     * @param orderBO
     */
    public OrderVO createOrder(OrderBO orderBO) throws Exception;

    /**
     * 更新订单状态
     * @param orderId
     * @param orderStatus
     */
    public void updateOrderStatus(String orderId,int orderStatus);

    /**
     * 查询订单状态
     * @param orderId
     * @return
     */
    public OrderStatus queryOrderStatus(String orderId);

}
