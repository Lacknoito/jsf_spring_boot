package com.erp.dashboard.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
@ManagedBean
@SessionScoped
public class UserModel {
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isUserEmpty() {
		return !StringUtils.isBlank(userName);
	}
}
