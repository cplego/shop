package org.cplego.foodie.service.impl;

import org.cplego.foodie.enums.Sex;
import org.cplego.foodie.utils.DateUtil;
import org.cplego.foodie.utils.MD5Utils;
import org.cplego.foodie.service.UserService;
import org.cplego.foodie.mapper.UsersMapper;
import org.cplego.foodie.pojo.Users;
import org.cplego.foodie.pojo.bo.UserBO;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean userNameIsExist(String username) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        Users users = usersMapper.selectOneByExample(example);
        return null == users ? false : true;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users saveUser(UserBO userBO) {

        Users user = new Users();
        user.setId(sid.nextShort());
        user.setUsername(userBO.getUsername());
        try {
            user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置默认的生日
        user.setBirthday(DateUtil.stringToDate("1970-01-01"));
        //设置默认的头像
        user.setFace(USER_FACE);
        //设置默认的性别
        user.setSex(Sex.security.type);
        //默认设置昵称与用户名相同
        user.setNickname(userBO.getUsername());
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());

        usersMapper.insert(user);
        return user;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username,String password) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        try {
            criteria.andEqualTo("password",MD5Utils.getMD5Str(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Users users = usersMapper.selectOneByExample(example);

        return users;
    }
}