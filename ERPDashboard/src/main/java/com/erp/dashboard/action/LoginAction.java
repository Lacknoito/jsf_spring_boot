package com.erp.dashboard.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.dashboard.ERPSession;
import com.erp.dashboard.model.User;
import com.erp.dashboard.service.IERPService;



@Component
@ManagedBean
@RequestScoped
public class LoginAction {
	private User userLogin;
	private String userName;
	private String password;
	
	@Autowired
	IERPService userService;
	
	@Autowired
	ERPSession erpSession;
	
	public void onload() {
		try {
			userLogin = erpSession.getUser();
			if(userLogin != null)
				FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard.jsf");
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

    public void onClickLogin() {
    	try {
//    		User user = userService.checkUserLogin(new User(userName, password));
    		User user = new User(userName, password);
	    	
	    	if(user != null) {
	    		erpSession.setUser(user);
	    		
	    		FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard.jsf");
	    	}else {
	    		FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
	    	}
    	}catch (Exception e) {

    	}
    }

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
}
