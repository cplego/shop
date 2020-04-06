package org.cplego.foodie.controller;

import org.cplego.foodie.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    //private RedisTemplate redisTemplate;
    private RedisOperator redisTemplate;

    @RequestMapping("set")
    public String setString(String key ,String value){
        redisTemplate.set(key,value);
        return "set ksy success";
    }

    @RequestMapping("get")
    public String getString(String key){
        String value = redisTemplate.get(key);
        return value;
    }
/*    @RequestMapping("del")
    public String delString(String key){
        redisTemplate.del(key);
        return "del key success";
    }*/

}