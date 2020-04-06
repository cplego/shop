package org.cplego.foodie.controller;

import org.cplego.foodie.pojo.Carousel;
import org.cplego.foodie.pojo.Category;
import org.cplego.foodie.pojo.vo.NewItemsVO;
import org.cplego.foodie.service.CarouselService;
import org.cplego.foodie.service.CategoryService;
import org.cplego.foodie.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/index")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/cats")
    public JSONResult getAllCategory(){

        List<Category>  list = categoryService.queryAllCategory();
        return JSONResult.ok(list);

    }

    @GetMapping("/subCat/{rootCatId}")
    public JSONResult getSubCategory(@PathVariable Integer rootCatId){
        return JSONResult.ok(categoryService.querySubCategoryList(rootCatId));

    }

    @GetMapping("/sixNewItems/{rootCatId}")
    public JSONResult sixNewItems(@PathVariable Integer rootCatId){
       List<NewItemsVO> list = categoryService.getSixNewItemsLazy(rootCatId);
       return JSONResult.ok(list);
    }

}
