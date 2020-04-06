package org.cplego.foodie.pojo.bo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

/**
 *      * Bean Validation 中内置的 constraint
 *      * @Null   被注释的元素必须为 null
 *      * @NotNull    被注释的元素必须不为 null
 *      * @AssertTrue     被注释的元素必须为 true
 *      * @AssertFalse    被注释的元素必须为 false
 *      * @Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 *      * @Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 *      * @DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 *      * @DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 *      * @Size(max=, min=)   被注释的元素的大小必须在指定的范围内
 *      * @Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内
 *      * @Past   被注释的元素必须是一个过去的日期
 *      * @Future     被注释的元素必须是一个将来的日期
 *      * @Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式
 *      * Hibernate Validator 附加的 constraint
 *      * @NotBlank(message =)   验证字符串非null，且长度必须大于0
 *      * @Email  被注释的元素必须是电子邮箱地址
 *      * @Length(min=,max=)  被注释的字符串的大小必须在指定的范围内
 *      * @NotEmpty   被注释的字符串的必须非空
 *      * @Range(min=,max=,message=)  被注释的元素必须在合适的范围内
 */
public class CenterUserBO {
	@NotEmpty(message="用户名不能为空")
	@Length(min = 0,max = 12,message = "用户名长度非法")
	private String username;
	@NotEmpty(message="姓名不能为空")
	@Length(min = 0,max = 12,message = "姓名长度非法")
	private String realname;
	@NotEmpty(message="昵称不能为空")
	@Length(min = 0,max = 12,message = "昵称长度非法")
	private String nickname;

	@Max(value = 2,message = "性别选择非法")
	@Min(value = 0,message = "性别选择非法")
	private Integer sex;
	//TODO 手机号 正则验证不通过
	//@Pattern(regexp = "/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$/",message = "手机号非法")
	private String mobile;

	@Email(message = "邮箱非法")
	private String email;

	private Date birthday;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "CenterUserBO{" +
				"username='" + username + '\'' +
				", realname='" + realname + '\'' +
				", nickname='" + nickname + '\'' +
				", sex='" + sex + '\'' +
				", mobile='" + mobile + '\'' +
				", email='" + email + '\'' +
				", birthday=" + birthday +
				'}';
	}
}
