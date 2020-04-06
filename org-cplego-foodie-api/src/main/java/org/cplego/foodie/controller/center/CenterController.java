package org.cplego.foodie.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cplego.foodie.pojo.Users;
import org.cplego.foodie.service.center.CenterUserService;
import org.cplego.foodie.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "用户中心接口" ,tags = "用户中心接口")
@RestController
@RequestMapping("/center")
public class CenterController {

	@Autowired
	private CenterUserService centerUserService;

	@ApiOperation(value = "初始化用户信息接口",notes = "初始化用户信息接口",httpMethod = "GET")
	@GetMapping("/userInfo")
	public JSONResult userInfo (String userId) {
        Users users = centerUserService.queryUser(userId);
        users.setPassword(null);
		return JSONResult.ok(users);
	}

}
