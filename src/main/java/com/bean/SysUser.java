package com.bean;

import java.util.Date;

/**
 * <p>
 * 用户管理
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-02-15
 */
@SuppressWarnings("serial")
public class SysUser extends BaseModel {

	/**
	 * 登陆帐户
	 */
	private String account;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 用户类型(1普通用户2管理员3系统用户)
	 */
	private String userType;
	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 姓名拼音
	 */
	private String namePinyin;
	/**
	 * 性别(0:未知;1:男;2:女)
	 */
	private Integer sex;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 身份证号码
	 */
	private String idCard;
	/**
	 * 微信
	 */
	private String weiXin;
	/**
	 * 微博
	 */
	private String weiBo;
	/**
	 * QQ
	 */
	private String qq;
	/**
	 * 出生日期
	 */
	private Date birthDay;
	/**
	 * 部门编号
	 */
	private Long deptId;
	/**
	 * 职位
	 */
	private String position;
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 工号
	 */
	private String staffNo;

	private String oldPassword;
	private String deptName;
	private String userTypeText;
	private String permission;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNamePinyin() {
		return namePinyin;
	}

	public void setNamePinyin(String namePinyin) {
		this.namePinyin = namePinyin;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getWeiXin() {
		return weiXin;
	}

	public void setWeiXin(String weiXin) {
		this.weiXin = weiXin;
	}

	public String getWeiBo() {
		return weiBo;
	}

	public void setWeiBo(String weiBo) {
		this.weiBo = weiBo;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserTypeText() {
		return userTypeText;
	}

	public void setUserTypeText(String userTypeText) {
		this.userTypeText = userTypeText;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}