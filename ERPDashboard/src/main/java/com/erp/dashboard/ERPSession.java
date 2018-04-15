package com.erp.dashboard;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

import com.erp.dashboard.model.User;

@Component
@ManagedBean
@SessionScoped
public class ERPSession {
	private User user;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
