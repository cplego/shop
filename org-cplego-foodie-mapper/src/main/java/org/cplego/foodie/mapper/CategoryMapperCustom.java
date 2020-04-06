package org.cplego.foodie.mapper;

import org.apache.ibatis.annotations.Param;
import org.cplego.foodie.pojo.vo.CategoryVO;
import org.cplego.foodie.pojo.vo.NewItemsVO;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom {
	/**
	 *
	 * @param rootCatId
	 * @return
	 */
	public List<CategoryVO> querySubCategoryList(Integer rootCatId);

	/**
	 * 查询首页推荐的6条商品
	 * @param map
	 * @return
	 */
	public List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String,Object> map);

}