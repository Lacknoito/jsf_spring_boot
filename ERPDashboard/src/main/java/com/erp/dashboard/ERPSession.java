package com.erp.dashboard;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.erp.dashboard.entity.User;

@SessionScope
@Component(value = "erpSession")
public class ERPSession {
	private User user;
	
	private static Logger logger = LogManager.getLogger(ERPSession.class);

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
