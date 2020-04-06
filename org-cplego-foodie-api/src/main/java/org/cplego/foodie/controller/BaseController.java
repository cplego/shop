package org.cplego.foodie.controller;

import org.cplego.foodie.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class BaseController {

    // 支付中心的调用地址
    String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";		// produce

    final String  CAROUSEL ="INDEX:CAROUSEL"; //轮播图

    final String SHOPCART = "shopcart";//购物车


}
