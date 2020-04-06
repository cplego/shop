package org.cplego.foodie.controller;

import org.apache.commons.lang3.StringUtils;
import org.cplego.foodie.pojo.*;
import org.cplego.foodie.pojo.vo.CommentsCountsVO;
import org.cplego.foodie.pojo.vo.ItemsVO;
import org.cplego.foodie.pojo.vo.NewItemsVO;
import org.cplego.foodie.pojo.vo.ShopcartVO;
import org.cplego.foodie.service.CategoryService;
import org.cplego.foodie.service.ItemService;
import org.cplego.foodie.utils.JSONResult;
import org.cplego.foodie.utils.PagedGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/items")
@RestController
public class ItemsController {

	@Autowired
	private ItemService itemService;
	@GetMapping("/info/{itemId}")
	public JSONResult info(@PathVariable String itemId){
		Items items = itemService.queryItemById(itemId);
		ItemsParam itemsParam = itemService.queryItemParam(itemId);
		List<ItemsSpec> itemsSpecs = itemService.queryItemSpecList(itemId);
		List<ItemsImg> itemsImgs = itemService.queryItemImgList(itemId);

		ItemsVO itemsVO = new ItemsVO();
		itemsVO.setItem(items);
		itemsVO.setItemParams(itemsParam);
		itemsVO.setItemImgList(itemsImgs);
		itemsVO.setItemSpecList(itemsSpecs);
		return JSONResult.ok(itemsVO);

	}
	@GetMapping("/commentLevel")
	public JSONResult queryCommentsCounts(@RequestParam String itemId) {
		CommentsCountsVO commentsCountsVO = itemService.queryCommentsCounts(itemId);
		return JSONResult.ok(commentsCountsVO);
	}

	@GetMapping("/comments")
	public JSONResult comments(@RequestParam String itemId,
	                           @RequestParam Integer level,
	                           @RequestParam Integer page,
	                           @RequestParam Integer pageSize) {

		if(page == null){
			page = 1;
		}
		if(pageSize == null) {
			pageSize = 10;
		}
		PagedGridResult pagedGridResult = itemService.queryItemsComments(itemId,level,page,pageSize);
		return JSONResult.ok(pagedGridResult);
	}

	@GetMapping("/search")
	public JSONResult search (@RequestParam String keywords,
	                          @RequestParam String sort,
	                          @RequestParam Integer page,
	                          @RequestParam Integer pageSize){
		if(page == null){
			page = 1;
		}
		if(pageSize == null) {
			pageSize = 10;
		}
		PagedGridResult pagedGridResult = itemService.serachItems(keywords,sort,page,pageSize);
		return JSONResult.ok(pagedGridResult);
	}

	@GetMapping("/catItems")
	public JSONResult catItems (@RequestParam String catId,
	                          @RequestParam String sort,
	                          @RequestParam Integer page,
	                          @RequestParam Integer pageSize){
		if(page == null){
			page = 1;
		}
		if(pageSize == null) {
			pageSize = 10;
		}
		PagedGridResult pagedGridResult = itemService.serachCatItems(catId,sort,page,pageSize);
		return JSONResult.ok(pagedGridResult);
	}
	@GetMapping("/refresh")
	public JSONResult refresh(@RequestParam String itemSpecIds){
		if(StringUtils.isBlank(itemSpecIds)){
			return JSONResult.errorMap("");
		}
		String [] strings = itemSpecIds.split(",");
		List<String> ispecList = Arrays.asList(strings);
		List<ShopcartVO>  shopcartVOS =  itemService.refreshShopcart(ispecList);
		return  JSONResult.ok(shopcartVOS);
 	}



}
