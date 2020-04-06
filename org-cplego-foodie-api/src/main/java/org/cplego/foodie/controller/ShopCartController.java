package org.cplego.foodie.controller;

import org.apache.commons.lang3.StringUtils;
import org.cplego.foodie.pojo.bo.ShopcartBO;
import org.cplego.foodie.utils.CookieUtils;
import org.cplego.foodie.utils.JSONResult;
import org.cplego.foodie.utils.JsonUtils;
import org.cplego.foodie.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shopcart")
public class ShopCartController extends BaseController{

    @Autowired
    private RedisOperator redisOperator;

    @PostMapping("/add")
    public JSONResult add(@RequestParam String userId,
                          @RequestBody ShopcartBO shopCartBO,//购物车对象
                          HttpServletRequest request,
                          HttpServletResponse response
                          ){
        if(StringUtils.isBlank(userId)){
            return JSONResult.errorMsg("");
        }
        System.out.println("shopcartBO -- "+shopCartBO.toString());
        // TODO 用户登录的情况下添加购物车需要购物车的商品同步到redis
        String shopcartJson = redisOperator.get(SHOPCART+":"+userId);
        List<ShopcartBO> shopcartBOList = null;
        if(StringUtils.isNotBlank(shopcartJson)){
            shopcartBOList = JsonUtils.jsonToList(shopcartJson,ShopcartBO.class);
            boolean isExist = false;
            for(ShopcartBO sbo : shopcartBOList){
                if(sbo.getSpecId().equals(shopCartBO.getSpecId())) {
                    sbo.setBuyCounts(sbo.getBuyCounts() + shopCartBO.getBuyCounts());
                    isExist = true;
                }
            }
            if(!isExist){
                shopcartBOList.add(shopCartBO);
            }
        }else{
            shopcartBOList = new ArrayList<ShopcartBO>();
            shopcartBOList.add(shopCartBO);
        }

        redisOperator.set(SHOPCART+":"+userId, JsonUtils.objectToJson(shopcartBOList));
        return JSONResult.ok();
    }
    @PostMapping("/del")
    public JSONResult del(
                          @RequestParam String userId,
                          @RequestParam String itemSpecId,
                          HttpServletRequest request,
                          HttpServletResponse response
                          ){
        if(StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)){
            return JSONResult.errorMsg("");
        }
        // TODO redis 操作
        return JSONResult.ok();
    }





}
