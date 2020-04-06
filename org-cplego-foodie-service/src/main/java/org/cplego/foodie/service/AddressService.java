package org.cplego.foodie.service;

import org.cplego.foodie.pojo.*;
import org.cplego.foodie.pojo.bo.UserAddressBO;
import org.cplego.foodie.pojo.vo.CommentsCountsVO;
import org.cplego.foodie.pojo.vo.ShopcartVO;
import org.cplego.foodie.utils.PagedGridResult;

import java.util.List;

/**
 * 收货地址service
 * 查询所有的收货地址
 * 新增收货地址
 * 修改收获地址
 * 删除收货地址
 * 设置默认收货地址
 *
 */
public interface AddressService {
    /**
     * 查询收货地址
     * @param userId
     * @return
     */
    public List<UserAddress> queryUserAddress(String userId);
    /**
     * 根据收货地址id查询收货地址
     * @param addressId
     * @return
     */
    public UserAddress queryUserAddres(String addressId);
    /**
     * 新增收货地址
     * @param userAddressBO
     */
    public void addUserAddress(UserAddressBO userAddressBO);

    /**
     * 设置默认地址
     * @param userId
     * @param addressId
     */
    public void setUserAddressDef(String userId,String addressId);

    /**
     * 修改收货地址
     * @param userAddressBO
     */
    public void updateUserAddress(UserAddressBO userAddressBO);
    /**
     * 删除收货地址
     * @param addressId
     */
    public void deleteUserAddress(String addressId);

}

