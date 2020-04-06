package org.cplego.foodie.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.cplego.foodie.enums.OrderStatusEnum;
import org.cplego.foodie.enums.PayMethod;
import org.cplego.foodie.pojo.OrderStatus;
import org.cplego.foodie.pojo.UserAddress;
import org.cplego.foodie.pojo.bo.OrderBO;
import org.cplego.foodie.pojo.bo.UserAddressBO;
import org.cplego.foodie.pojo.vo.MerchantOrdersVO;
import org.cplego.foodie.pojo.vo.OrderVO;
import org.cplego.foodie.service.AddressService;
import org.cplego.foodie.service.OrderService;
import org.cplego.foodie.utils.CookieUtils;
import org.cplego.foodie.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "订单相关接口" ,tags = "订单相关接口")
@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController{
    @Autowired
    private OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "创建订单",notes = "创建订单",httpMethod = "POST")
    @PostMapping("/create")
    public JSONResult list(@RequestBody OrderBO orderBO, HttpServletRequest request
                            ,HttpServletResponse response) throws Exception {
        if(orderBO.getPayMethod() != PayMethod.WEIXIN.type && orderBO.getPayMethod()!= PayMethod.ALIPAY.type){
            return JSONResult.errorMsg("支付方式不支持");
        }

        System.out.println("orderBO --> "+orderBO.toString());

        OrderVO  orderVO=   orderService.createOrder(orderBO);
        // TODO 订单创建后移除购物车中已经结算的商品 需要整合redis
        //移除前端cookis购物车中的商品
        CookieUtils.setCookie(request,response,"shopcart","",true);

        //向支付中心发送当前订单，用于保存支付中心的订单数据
        MerchantOrdersVO merchantOrdersVO = new MerchantOrdersVO();
        merchantOrdersVO.setAmount(orderVO.getAmount());
        merchantOrdersVO.setMerchantOrderId(orderVO.getMerchantOrderId());
        merchantOrdersVO.setReturnUrl(orderVO.getReturnUrl());
        merchantOrdersVO.setPayMethod(orderBO.getPayMethod());
        merchantOrdersVO.setMerchantUserId(orderBO.getUserId());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("imoocUserId","8420286-1401579045");
        httpHeaders.add("password","powl-0rok-fkj4-0tkm");
        HttpEntity httpEntity = new HttpEntity(merchantOrdersVO,httpHeaders);
        ResponseEntity<JSONResult>  responseEntity= restTemplate.postForEntity(paymentUrl,httpEntity,JSONResult.class);
        JSONResult payResult = responseEntity.getBody();

        System.out.println("payResult -- > "+payResult.getMsg());

        if(payResult.getStatus() != 200){

            return JSONResult.errorMsg("支付中心创建订单失败，请联系管理员");
        }
        return JSONResult.ok(orderVO.getMerchantOrderId());
    }

    @ApiOperation(value = "更新订单状态",notes = "支付中心回调更新订单状态",httpMethod = "POST")
    @PostMapping("/notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(@RequestParam String merchantOrderId){

        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);

        return HttpStatus.OK.value();
    }

    @ApiOperation(value = "查询订单状态",notes = "查询订单状态",httpMethod = "POST")
    @PostMapping("/getPaidOrderInfo")
    public JSONResult getPaidOrderInfo(String orderId){
        if(StringUtils.isBlank(orderId)){
            return JSONResult.errorMsg("");
        }
        OrderStatus orderStatus = orderService.queryOrderStatus(orderId);
        return JSONResult.ok(orderStatus);
    }

}
