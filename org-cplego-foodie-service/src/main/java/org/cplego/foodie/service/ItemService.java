package org.cplego.foodie.service;

import org.cplego.foodie.pojo.*;
import org.cplego.foodie.pojo.vo.CommentsCountsVO;
import org.cplego.foodie.pojo.vo.ItemsCommentsVO;
import org.cplego.foodie.pojo.vo.ItemsSerachVO;
import org.cplego.foodie.pojo.vo.ShopcartVO;
import org.cplego.foodie.utils.PagedGridResult;

import java.util.List;

/**
 * 商品详情Service
 */
public interface ItemService {

    /**
     * 根据商品ID查询详情
     * @param itemId
     * @return
     */
    public Items queryItemById(String itemId);

    /**
     * 根据商品id查询商品图片列表
     * @param itemId
     * @return
     */
    public List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品id查询商品主图图片
     * @param itemId
     * @return
     */
    public ItemsImg queryItemImg(String itemId);

    /**
     * 根据商品id查询商品规格
     * @param itemId
     * @return
     */
    public List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品id查询商品参数
     * @param itemId
     * @return
     */
    public ItemsParam queryItemParam(String itemId);
    /**
     * 根据商品id查询商品评论等级总数
     * @param itemId
     * @return
     */
    public CommentsCountsVO queryCommentsCounts(String itemId);
    /**
     * 根据商品id,商品等级,查询商品评论
     * @param itemId
     * @return
     */
    public PagedGridResult queryItemsComments(String itemId, Integer level,Integer pageNum,Integer pageSize);

    /**
     * 搜索商品
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult serachItems(String keywords,String sort,Integer page,Integer pageSize);
    /**
     * 搜索商品
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult serachCatItems(String catId,String sort,Integer page,Integer pageSize);

    /**
     * 刷新购物车商品数据
     * @param list
     * @return
     */
    public List<ShopcartVO> refreshShopcart(List<String> list);
    /**
     * 根据商品规格id查询商品规格
     * @param itemSpecId
     * @return
     */
    public ItemsSpec queryItemSpec(String itemSpecId);

    /**
     * 减少商品对应规格的库存
     * @param itemSpecId 商品规格id
     * @param bugCount 购买数量
     */
    public void descItemsSpecStock(String itemSpecId,int bugCount) throws Exception;

}

