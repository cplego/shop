package org.cplego.foodie.service.impl;

import org.cplego.foodie.mapper.CategoryMapper;
import org.cplego.foodie.mapper.CategoryMapperCustom;
import org.cplego.foodie.pojo.Category;
import org.cplego.foodie.pojo.vo.CategoryVO;
import org.cplego.foodie.pojo.vo.NewItemsVO;
import org.cplego.foodie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryAllCategory() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type",1);
        List<Category> list  = categoryMapper.selectByExample(example);
        return list;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryVO> querySubCategoryList(Integer rootCatId){
        return categoryMapperCustom.querySubCategoryList(rootCatId);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId) {

        Map<String,Object> map = new HashMap<>();
        map.put("rootCatId",rootCatId);
        return categoryMapperCustom.getSixNewItemsLazy(map);

    }
}