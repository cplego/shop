package org.cplego.foodie.service.impl;

import org.apache.catalina.User;
import org.cplego.foodie.enums.IsDefAddress;
import org.cplego.foodie.mapper.StuMapper;
import org.cplego.foodie.mapper.UserAddressMapper;
import org.cplego.foodie.pojo.Stu;
import org.cplego.foodie.pojo.UserAddress;
import org.cplego.foodie.pojo.bo.UserAddressBO;
import org.cplego.foodie.service.AddressService;
import org.cplego.foodie.service.StuService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserAddress> queryUserAddress(String userId){
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        Example example = new Example(UserAddress.class);
        Example.Criteria criteria= example.createCriteria();
        criteria.andEqualTo(userAddress);
        List<UserAddress> userAddresses = userAddressMapper.selectByExample(example);
        return  userAddresses;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addUserAddress(UserAddressBO userAddressBO) {

        Integer defaultAddress = 0; //默认地址

        //1. 首选确认该用户是不是已经存在收货地址，不存在收货地址则设置默认收货地址
        List <UserAddress>  userAddresses = this.queryUserAddress(userAddressBO.getUserId());
        if (userAddresses.size() == 0 || null == userAddresses || userAddresses.isEmpty() ){
            defaultAddress = 1;
        }
        //2. 新增收货地址
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressBO,userAddress);
        userAddress.setId(sid.nextShort());
        userAddress.setIsDefault(defaultAddress);
        userAddress.setCreatedTime(new Date());
        userAddress.setUpdatedTime(new Date());
        userAddressMapper.insert(userAddress);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void setUserAddressDef(String userId, String addressId) {
        //1. 把默认的地址设为非默认
       UserAddress userAddress = new UserAddress();
       userAddress.setUserId(userId);
       userAddress.setIsDefault(IsDefAddress.IS_DEF_ADDRESS.type);
       UserAddress  u = userAddressMapper.selectOne(userAddress);
       if(null != u){
        u.setIsDefault(IsDefAddress.NOT.type);
        userAddressMapper.updateByPrimaryKey(u);
       }

        //2. 根据参数设置指定的地址为默认地址
        UserAddress sub = new UserAddress();
        sub.setUserId(userId);
        sub.setId(addressId);
        UserAddress  u2 = userAddressMapper.selectOne(sub);
        u2.setIsDefault(IsDefAddress.IS_DEF_ADDRESS.type);
        userAddressMapper.updateByPrimaryKey(u2);
    }

    @Transactional(propagation =  Propagation.REQUIRED)
    @Override
    public void updateUserAddress(UserAddressBO userAddressBO) {

        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressBO,userAddress);
        userAddress.setId(userAddressBO.getAddressId());
        userAddress.setUpdatedTime(new Date());
        userAddressMapper.updateByPrimaryKeySelective(userAddress);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteUserAddress(String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(addressId);
        userAddressMapper.deleteByPrimaryKey(userAddress);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public UserAddress queryUserAddres(String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(addressId);
        return userAddressMapper.selectOne(userAddress);
    }
}


