package org.cplego.foodie.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.cplego.foodie.pojo.Users;
import org.cplego.foodie.pojo.bo.CenterUserBO;
import org.cplego.foodie.resouce.Fileupload;
import org.cplego.foodie.service.center.CenterUserService;
import org.cplego.foodie.utils.CookieUtils;
import org.cplego.foodie.utils.DateUtil;
import org.cplego.foodie.utils.JSONResult;
import org.cplego.foodie.utils.JsonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Api(value = "用户中心用户信息接口" ,tags = "用户中心用户信息接口")
@RestController
@RequestMapping("/userInfo")
public class CenterUserController {

	@Autowired
	private CenterUserService centerUserService;
	@Autowired
	private Fileupload fileupload;

	@ApiOperation(value = "修改用户信息接口",notes = "修改用户信息接口",httpMethod = "POST")
	@PostMapping("/update")
	public JSONResult userInfo (String userId ,
	                            @RequestBody @Valid CenterUserBO userInfoMore,
	                            BindingResult bindingResult,
	                            HttpServletRequest request, HttpServletResponse response
	                            ) {
		System.out.println("--> " + userInfoMore.toString());
		//通过hibernate 字段校验模块 校验字段合法性

		if (bindingResult.hasErrors()){
			Map resultMap = getErrors(bindingResult);
			return JSONResult.errorMap(resultMap);
		}
		Users users = new Users();
		BeanUtils.copyProperties(userInfoMore,users);
		Users u = centerUserService.updateUser(userId,users);
		//设置前端cookies信息
		CookieUtils.setCookie(
				request,response,"user", JsonUtils.objectToJson(u),true
		);
		return JSONResult.ok();
	}
	private Map<String, String> getErrors(BindingResult result) {
		Map<String, String> map = new HashMap<String, String>();
		List<FieldError> list = result.getFieldErrors();
		for (FieldError error : list) {
			System.out.println("error.getField():" + error.getField());
			System.out.println("error.getDefaultMessage():" + error.getDefaultMessage());
			map.put(error.getField(), error.getDefaultMessage());
		}
		return map;
	}

	@ApiOperation(value = "上传用户头像接口",notes = "上传用户头像接口",httpMethod = "POST")
	@PostMapping("/uploadFace")
	public JSONResult uploadFace(String userId, MultipartFile file,HttpServletRequest request, HttpServletResponse response){
		//String basePath = "D:\\foodie-images\\face";
		String basePath = fileupload.getImgUploadLoaction();
		//开始上传文件
		if(file == null) { return JSONResult.errorMsg(""); }
			String filename = file.getOriginalFilename();
			String [] strs = filename.split("\\.");
			String filetype = strs[strs.length-1];
			if (! filetype.equalsIgnoreCase("jpg")
			&& ! filetype.equalsIgnoreCase("png")
			&& ! filetype.equalsIgnoreCase("jpeg")){
				return JSONResult.errorMsg("图片格式非法");
			}
			String newFileName ="face_" + userId + "." + filetype;
			String newFile = basePath + "\\" + newFileName;
			InputStream inputStream = null;
			OutputStream outPutStream = null;
			try {
				File oFile = new File(newFile);
				inputStream = file.getInputStream();
				outPutStream = new FileOutputStream(oFile);
				IOUtils.copy(inputStream,outPutStream);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (null != inputStream) {
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}if (null != outPutStream) {
					try {
						outPutStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}

		String face = fileupload.getProPath()+newFileName + "?t="+ DateUtil.getCurrentDateString(DateUtil.DATE_PATTERN);

		Users resultUser = centerUserService.updateUserFace(userId,face);
		CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(resultUser),true);
		return JSONResult.ok();
	}





}
