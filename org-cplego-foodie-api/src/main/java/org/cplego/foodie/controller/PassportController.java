package org.cplego.foodie.controller;

import org.apache.commons.lang3.StringUtils;
import org.cplego.foodie.pojo.bo.ShopcartBO;
import org.cplego.foodie.utils.CookieUtils;
import org.cplego.foodie.utils.JSONResult;
import org.cplego.foodie.utils.JsonUtils;
import org.cplego.foodie.service.UserService;
import org.cplego.foodie.pojo.Users;
import org.cplego.foodie.pojo.bo.UserBO;
import org.cplego.foodie.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/passport")
public class PassportController extends BaseController{
    @Autowired
    private UserService userService;
    @Autowired
    private RedisOperator redisOperator;


    @GetMapping("/usernameIsExist")
    public JSONResult getStu(String username){

        if(StringUtils.isEmpty(username)){
            return JSONResult.errorMsg("用户名不能为空");
        }
        boolean exist = userService.userNameIsExist(username);
        if(exist){
            return JSONResult.errorMsg("用户名已经存在");

        }else {
            return JSONResult.ok();
        }
    }

    @PostMapping("/regist")
    public JSONResult regist(@RequestBody UserBO userBO,HttpServletRequest request,HttpServletResponse response){
        System.out.println("in regist -- >" + userBO.getUsername());
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();

        //1. 用户信息为空直接返回
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPassword)){
            return JSONResult.errorMsg("用户名或密码为空");

        }
        //2. 密码长度小于6位直接返回
        if(password.length() < 6)
            return JSONResult.errorMsg("密码长度需大于等于6");
        //3. 密码与确认密码不一致直接返回
        if(!password.equals(confirmPassword))
            return JSONResult.errorMsg("两次密码输入不一致");
        //4. 判断用户名是否已经存在
        boolean exist = userService.userNameIsExist(username);
        if(exist) {
            return JSONResult.errorMsg("用户名已经存在");
        }
        Users users = userService.saveUser(userBO);
        setNullValue(users);
        CookieUtils.setCookie(request,response,"user",
                JsonUtils.objectToJson(users),true);
        return JSONResult.ok();

    }

    @PostMapping("/login")
    public JSONResult login(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response){
        System.out.println("userbo --> "+userBO.getUsername());
        //1. 用户名或密码为空直接返回
        if(StringUtils.isBlank(userBO.getPassword())
                || StringUtils.isBlank(userBO.getUsername())){
            return JSONResult.errorMsg("用户名或密码不能为空");
        }

        Users users = userService.queryUserForLogin(userBO.getUsername(),userBO.getPassword());
        if(null == users){
            //2. 查询返回结果为空返回用户名或者密码不存在
            return JSONResult.errorMsg("用户名或密码错误");
        }else{
            //3. 查询成功设置cookies信息并返回OK
         /*   HttpSession session = request.getSession();
            session.setAttribute("userInfo",users);*/
           /* response.addCookie(new Cookie("Pjhname","Pjh"));*/
            setNullValue(users);
            CookieUtils.setCookie(request,response,"user",
                    JsonUtils.objectToJson(users),true);
            }

            return JSONResult.ok(users);


    }
    private  void setNullValue(Users users ){
        users.setPassword(null);
        users.setUpdatedTime(null);
        users.setCreatedTime(null);
        users.setEmail(null);
        users.setMobile(null);
        users.setRealname(null);
    }

    @PostMapping("/logout")
    public JSONResult logout(HttpServletRequest request,HttpServletResponse response){
        CookieUtils.deleteCookie(request,response,"user");
        return JSONResult.ok();
    }

}
