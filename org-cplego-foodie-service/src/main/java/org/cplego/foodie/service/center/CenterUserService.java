package org.cplego.foodie.service.center;

import org.cplego.foodie.pojo.Users;
import org.cplego.foodie.pojo.bo.UserBO;

public interface CenterUserService {

    /**
     * 初始化用户信息
     * @param userId
     * @return
     */
    public Users queryUser(String userId);

    /**
     * 修改用户信息
     * @param users
     */
    public Users updateUser(String userId,Users users);

    /**
     * 修改用户的头像
     * @param userId
     * @param userFace
     * @return
     */
    public Users updateUserFace(String userId,String userFace);
}
