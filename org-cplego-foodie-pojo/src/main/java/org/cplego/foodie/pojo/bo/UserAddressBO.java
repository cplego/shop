package org.cplego.foodie.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户地址BO",description = "前端传入的用户地址相关参数")
public class UserAddressBO {
    @ApiModelProperty(value = "收获地址ID",name = "address",example = "")
    private String addressId;
    @ApiModelProperty(value = "用户ID",name = "userId",example = "test",required = true)
    private String userId;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
    @ApiModelProperty(value = "收货人",name = "receiver",example = "test",required = true)
    private String receiver; // 收货人
    @ApiModelProperty(value = "手机号码",name = "mobile",example = "13227699933",required = true)
    private String mobile;
    @ApiModelProperty(value = "省份",name = "province",example = "广东省",required = true)
    private String province;
    @ApiModelProperty(value = "城市",name = "city",example = "广州市",required = true)
    private String city;
    @ApiModelProperty(value = "城区",name = "district",example = "番禺区",required = true)
    private String district;
    @ApiModelProperty(value = "详细地址",name = "detail",example = "花街5号",required = true)
    private String detail;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
