package org.cplego.foodie.pojo.vo;

import org.cplego.foodie.pojo.Items;
import org.cplego.foodie.pojo.ItemsImg;
import org.cplego.foodie.pojo.ItemsParam;
import org.cplego.foodie.pojo.ItemsSpec;

import java.util.List;

public class ItemsSerachVO {
	private String itemId;
	private String itemName;
	private Integer sellCounts;
	private String imgUrl;
	private Double price;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getSellCounts() {
		return sellCounts;
	}

	public void setSellCounts(Integer sellCounts) {
		this.sellCounts = sellCounts;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
