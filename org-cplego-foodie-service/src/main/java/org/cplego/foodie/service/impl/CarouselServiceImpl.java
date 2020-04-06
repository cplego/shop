package org.cplego.foodie.service.impl;

import org.cplego.foodie.mapper.CarouselMapper;
import org.cplego.foodie.pojo.Carousel;
import org.cplego.foodie.pojo.Category;
import org.cplego.foodie.service.CarouselService;
import org.cplego.foodie.service.CategoryService;
import org.cplego.foodie.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper = null;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Carousel> queryAllCarousel(int isShow) {
        Example example = new Example(Carousel.class);
        example.orderBy("sort").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow",isShow);
        List<Carousel> list = carouselMapper.selectByExample(example);

        return list;
    }
}