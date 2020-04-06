package org.cplego.foodie.controller;

import org.cplego.foodie.pojo.Carousel;
import org.cplego.foodie.service.CarouselService;
import org.cplego.foodie.service.StuService;
import org.cplego.foodie.utils.JSONResult;
import org.cplego.foodie.utils.JsonUtils;
import org.cplego.foodie.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/index")
@RestController
public class CarouselController extends BaseController{
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private RedisOperator redisOperator;

    @GetMapping("/carousel")
    public JSONResult getCarousel(){
        String carousels = redisOperator.get(CAROUSEL); //从redis中获取轮播图
        System.out.println("CAROUSEL : "+carousels);
        if(! StringUtils.isEmpty(carousels)){
            System.out.println("从redis中取得轮播图");
            return JSONResult.ok(JsonUtils.jsonToList(carousels,Carousel.class));
        }
        List<Carousel> list = carouselService.queryAllCarousel(1);
        redisOperator.set(CAROUSEL, JsonUtils.objectToJson(list),3600); //设置一个小时的过期时间
        return JSONResult.ok(list);

    }

}
