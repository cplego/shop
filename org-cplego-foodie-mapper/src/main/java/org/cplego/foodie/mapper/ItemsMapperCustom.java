package org.cplego.foodie.mapper;

import org.apache.ibatis.annotations.Param;
import org.cplego.foodie.my.mapper.MyMapper;
import org.cplego.foodie.pojo.Items;
import org.cplego.foodie.pojo.vo.ItemsCommentsVO;
import org.cplego.foodie.pojo.vo.ItemsSerachVO;
import org.cplego.foodie.pojo.vo.ShopcartVO;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom{
	/**
	 * 查询商品评价
	 * @param map
	 * @return
	 */
	public List<ItemsCommentsVO> queryItemsComments(@Param("paramsMap") Map<String,Object> map);

	/**
	 * 商品搜索功能
	 * @param map
	 * @return
	 */
	public List<ItemsSerachVO> serachItems (@Param("paramsMap") Map<String,Object> map);
	/**
	 * 商品搜索功能 前端三级分类id触发
	 * @param map
	 * @return
	 */
	public List<ItemsSerachVO> serachCatItems (@Param("paramsMap") Map<String,Object> map);
	/**
	 * cookie购物车商品信息刷新
	 * @param list
	 * @return
	 */
	public List<ShopcartVO> refreshShopcart (@Param("paramsList") List<String> list);

	/**
	 * 减少对应规格商品库存
	 * @param buyCount
	 * @param itemSpecId
	 * @return
	 */
	public Integer descItemsSpecStock(@Param("itemSpecId") String itemSpecId, @Param("buyCount") int buyCount);


}