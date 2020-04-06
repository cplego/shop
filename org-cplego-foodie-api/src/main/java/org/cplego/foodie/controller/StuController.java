package org.cplego.foodie.controller;

import org.apache.commons.lang3.StringUtils;
import org.cplego.foodie.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class StuController {
    @Autowired
    private StuService stuService;

    @GetMapping("/wish")
    public Object wish(String dream){
        System.out.println("dream " +dream);
        return "爸爸收到你的愿望了，安心乖宝宝";
    }
    @GetMapping("/sweet")
    public Object sweet(String bathday){
        if (StringUtils.isBlank(bathday) || bathday.length() != 8) return "说出你的生日：" +
                "http://qatwqe.natappfree.cc/sweet?bathday=生日：格式为：yyyyMMdd";
        System.out.println("-->" + bathday);
        return "最爱狗皮小可爱，你生日果然是:"+bathday+"  说出你的愿望把？" +
                "请输入 http://qatwqe.natappfree.cc/wish?dream=你的愿望";
    }
}