package com.erp.dashboard;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.erp.dashboard.entity.User;

@Component
@SessionScope
@ManagedBean
@SessionScoped
public class ERPSession {
	private User user;
	private String test = "Test";
	
	private static Logger logger = LogManager.getLogger(ERPSession.class);

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
}
