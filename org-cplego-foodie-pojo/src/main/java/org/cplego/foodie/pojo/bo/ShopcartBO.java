package org.cplego.foodie.pojo.bo;

public class ShopcartBO {

	private String itemId; //商品id
	private String itemImgUrl;//商品图片
	private String itemName;//商品名称
	private String specId;//规格id
	private String specName;//规格名称
	private Integer buyCounts;//购买数量
	private String priceDiscount;//打折价格
	private String priceNormal;//原价格

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemImgUrl() {
		return itemImgUrl;
	}

	public void setItemImgUrl(String itemImgUrl) {
		this.itemImgUrl = itemImgUrl;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public Integer getBuyCounts() {
		return buyCounts;
	}

	public void setBuyCounts(Integer buyCounts) {
		this.buyCounts = buyCounts;
	}

	public String getPriceDiscount() {
		return priceDiscount;
	}

	public void setPriceDiscount(String priceDiscount) {
		this.priceDiscount = priceDiscount;
	}

	public String getPriceNormal() {
		return priceNormal;
	}

	public void setPriceNormal(String priceNormal) {
		this.priceNormal = priceNormal;
	}

	@Override
	public String toString() {
		return "ShopCartBO{" +
				"itemId='" + itemId + '\'' +
				", itemImgUrl='" + itemImgUrl + '\'' +
				", itemName='" + itemName + '\'' +
				", specId='" + specId + '\'' +
				", specName='" + specName + '\'' +
				", buyCounts=" + buyCounts +
				", priceDiscount='" + priceDiscount + '\'' +
				", priceNormal='" + priceNormal + '\'' +
				'}';
	}
}
