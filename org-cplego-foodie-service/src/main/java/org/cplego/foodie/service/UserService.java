package org.cplego.foodie.service;

import org.cplego.foodie.pojo.Users;
import org.cplego.foodie.pojo.bo.UserBO;

public interface UserService {

    public boolean userNameIsExist(String username);

    public Users saveUser(UserBO userBO);

    public Users queryUserForLogin(String username,String password);

}
