package org.cplego.foodie.pojo.vo;

import org.cplego.foodie.pojo.Items;
import org.cplego.foodie.pojo.ItemsImg;
import org.cplego.foodie.pojo.ItemsParam;
import org.cplego.foodie.pojo.ItemsSpec;

import java.util.List;

public class ItemsVO {

	private Items item;
	private ItemsParam itemParams;
	private List<ItemsSpec> itemSpecList;
	private List<ItemsImg> itemImgList;

	public Items getItem() {
		return item;
	}

	public void setItem(Items item) {
		this.item = item;
	}

	public ItemsParam getItemParams() {
		return itemParams;
	}

	public void setItemParams(ItemsParam itemParams) {
		this.itemParams = itemParams;
	}

	public List<ItemsSpec> getItemSpecList() {
		return itemSpecList;
	}

	public void setItemSpecList(List<ItemsSpec> itemSpecList) {
		this.itemSpecList = itemSpecList;
	}

	public List<ItemsImg> getItemImgList() {
		return itemImgList;
	}

	public void setItemImgList(List<ItemsImg> itemImgList) {
		this.itemImgList = itemImgList;
	}
}
