package org.cplego.foodie.pojo.vo;

public class SubCategoryVO {
	private Integer subId;
	private String subName;
	private Integer subType;
	private Integer subFatherId;

	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public Integer getSubType() {
		return subType;
	}

	public void setSubType(Integer subType) {
		this.subType = subType;
	}

	public Integer getSubFatherId() {
		return subFatherId;
	}

	public void setSubFatherId(Integer subFatherId) {
		this.subFatherId = subFatherId;
	}
}
