package org.cplego.foodie.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.cplego.foodie.mapper.UserAddressMapper;
import org.cplego.foodie.pojo.UserAddress;
import org.cplego.foodie.pojo.bo.UserAddressBO;
import org.cplego.foodie.service.AddressService;
import org.cplego.foodie.service.StuService;
import org.cplego.foodie.utils.JSONResult;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "收货地址相关接口" ,tags = "收货地址相关接口")
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "获得用户所有的收货地址",notes = "获得用户所有的收获地址",httpMethod = "POST")
    @PostMapping("/list")
    public JSONResult list(@RequestParam String userId){

         List<UserAddress> userAddressList =  addressService.queryUserAddress(userId);
         return JSONResult.ok(userAddressList);
    }
    @ApiOperation(value = "新增用户收货地址",notes = "新增用户收获地址",httpMethod = "POST")
    @PostMapping("/add")
    public JSONResult add(@RequestBody UserAddressBO userAddressBO){
        addressService.addUserAddress(userAddressBO);
        return JSONResult.ok();
    }
    @PostMapping("/setDefalut")
    public JSONResult setDefalut(@RequestParam String userId,
                                 @RequestParam String addressId){
        if(StringUtils.isBlank(userId) || StringUtils.isBlank(addressId))
            return JSONResult.errorMsg("参数不能为空");

        addressService.setUserAddressDef(userId,addressId);

        return JSONResult.ok();
    }

    @PostMapping("/update")
    public JSONResult update(@RequestBody UserAddressBO userAddressBO){

        addressService.updateUserAddress(userAddressBO);

        return JSONResult.ok();
    }
    @PostMapping("/delete")
    public JSONResult delete(@RequestParam String addressId){

        addressService.deleteUserAddress(addressId);
        return JSONResult.ok();
    }


}
