package com.erp.dashboard.model;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component(value = "userModel")
public class UserModel {
	private String userName;
	private String password;
	
	private static Logger logger = LogManager.getLogger(UserModel.class);
	
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
