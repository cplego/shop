package org.cplego.foodie.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.cplego.foodie.enums.CommentsLevel;
import org.cplego.foodie.enums.YseOrNo;
import org.cplego.foodie.mapper.*;
import org.cplego.foodie.pojo.*;
import org.cplego.foodie.pojo.vo.CommentsCountsVO;
import org.cplego.foodie.pojo.vo.ItemsCommentsVO;
import org.cplego.foodie.pojo.vo.ItemsSerachVO;
import org.cplego.foodie.pojo.vo.ShopcartVO;
import org.cplego.foodie.service.ItemService;
import org.cplego.foodie.utils.DesensitizationUtil;
import org.cplego.foodie.utils.PagedGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper; //商品列表
    @Autowired
    private ItemsParamMapper itemsParamMapper; //商品参数
    @Autowired
    private ItemsImgMapper itemsImgMapper;//商品图片
    @Autowired
    private ItemsSpecMapper itemsSpecMapper;//商品规格

    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper; //商品评论
    @Autowired
    private ItemsMapperCustom itemsMapperCustom; //自定义Mapper

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryItemById(String itemId) {

        Example example = new Example(Items.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",itemId);
        return itemsMapper.selectOneByExample(example);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return itemsImgMapper.selectByExample(example);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        Example example = new Example(ItemsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return itemsSpecMapper.selectByExample(example);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemParam(String itemId) {
        Example example = new Example(ItemsParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return itemsParamMapper.selectOneByExample(example);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentsCountsVO queryCommentsCounts(String itemId) {
        Integer goodLevel = getCommentsCounts(itemId, CommentsLevel.GOOD.type); //好评
        Integer normalLevel = getCommentsCounts(itemId, CommentsLevel.NORMAL.type); //中评
        Integer badLevel= getCommentsCounts(itemId, CommentsLevel.BAD.type); //差评
        Integer counts = goodLevel + normalLevel + badLevel;
        CommentsCountsVO commentsCountsVO = new CommentsCountsVO(counts,goodLevel,normalLevel,badLevel);
        return commentsCountsVO;
    }

    /**
     * 根据商品id 与 评价等级 查询对应的评价数
     * @param itemId
     * @param level
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    Integer getCommentsCounts(String itemId, Integer level){
        ItemsComments itemsComments = new ItemsComments();
        itemsComments.setItemId(itemId);
        itemsComments.setCommentLevel(level);
        return itemsCommentsMapper.selectCount(itemsComments);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryItemsComments(String itemId, Integer level,
                                                    Integer pageNum,Integer pageSize) {
        Map <String ,Object> paramsMap = new HashMap<>();
        paramsMap.put("itemId",itemId);
        paramsMap.put("level",level);
        //PageHelper 分页插件
        Page page = PageHelper.startPage(pageNum,pageSize);

        List<ItemsCommentsVO> list = itemsMapperCustom.queryItemsComments(paramsMap);
        //昵称 脱敏处理
        for(int i = 0 ; i< list.size() ; i++){
            list.get(i).setNickname(DesensitizationUtil.commonDisplay(list.get(i).getNickname()));
        }
        return setPageGridResult(pageNum,page,list);
    }

    private PagedGridResult setPageGridResult(Integer pageNum,Page page,List<?>list){
        PagedGridResult pagedGridResult = new PagedGridResult();
        pagedGridResult.setPage(pageNum); // 设置当前页数
        pagedGridResult.setRows(list);//设置当前页内容
        pagedGridResult.setTotal(page.getPages()); //设置总页数
        pagedGridResult.setRecords(page.getTotal());//设置总记录数
        return pagedGridResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult serachItems(String keywords, String sort,
                                           Integer page, Integer pageSize) {

        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("keywords",keywords);
        paramsMap.put("sort",sort);
        //分页插件
        Page subPage = PageHelper.startPage(page,pageSize);
        List<ItemsSerachVO> list = itemsMapperCustom.serachItems(paramsMap);
        return
        setPageGridResult(page,subPage,list);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult serachCatItems(String catId, String sort, Integer page, Integer pageSize) {
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("catId",catId);
        paramsMap.put("sort",sort);
        //分页插件
        Page subPage = PageHelper.startPage(page,pageSize);
        List<ItemsSerachVO> list = itemsMapperCustom.serachCatItems(paramsMap);
        return
                setPageGridResult(page,subPage,list);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ShopcartVO> refreshShopcart(List<String> list) {
        return itemsMapperCustom.refreshShopcart(list);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsSpec queryItemSpec(String itemSpecId) {
        ItemsSpec itemsSpec = new ItemsSpec();
        itemsSpec.setId(itemSpecId);
        return itemsSpecMapper.selectOne(itemsSpec);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsImg queryItemImg(String itemId) {
        ItemsImg itemsImg = new ItemsImg();
        itemsImg.setIsMain(YseOrNo.Y.type);
        itemsImg.setItemId(itemId);
        return itemsImgMapper.selectOne(itemsImg);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void descItemsSpecStock(String itemSpecId, int buyCount) throws Exception {
        int result = itemsMapperCustom.descItemsSpecStock(itemSpecId,buyCount);
        if(result != 1) throw new Exception();

    }
}