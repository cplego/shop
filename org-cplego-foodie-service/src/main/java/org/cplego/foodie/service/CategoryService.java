package org.cplego.foodie.service;

import org.cplego.foodie.pojo.Category;
import org.cplego.foodie.pojo.Users;
import org.cplego.foodie.pojo.bo.UserBO;
import org.cplego.foodie.pojo.vo.CategoryVO;
import org.cplego.foodie.pojo.vo.NewItemsVO;

import java.util.List;

public interface CategoryService {

    public List<Category> queryAllCategory();
    public List<CategoryVO> querySubCategoryList(Integer rootCatId);
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);

}
