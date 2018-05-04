package com.erp.dashboard.action;

import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.erp.dashboard.ERPSession;
import com.erp.dashboard.entity.User;
import com.erp.dashboard.model.UserModel;
import com.erp.dashboard.service.IERPService;

@RequestScope
@Component(value = "loginController")
public class LoginController {
	private User userLogin;
	private String errorMessage;
	
	@Autowired
	private IERPService userService;
	@Autowired
	private ERPSession erpSession;
	@Autowired
	private UserModel userModel;
	
	private static Logger logger = LogManager.getLogger(LoginController.class);
	
	public void onload() {
		try {
			errorMessage = null;
			
			userLogin = erpSession.getUser();
			if(userLogin != null)
				FacesContext.getCurrentInstance().getExternalContext().redirect("cop_dashboard.jsf");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean validate() {
		boolean result = true;
		
		errorMessage = "";
		
		if(StringUtils.isBlank(userModel.getUserName()) 
				&& StringUtils.isBlank(userModel.getPassword())) {
			errorMessage = "Please enter username and password";
			
			result = false;
		}else if(StringUtils.isBlank(userModel.getUserName())) {
			errorMessage = "Please enter username";
			
			result = false;
		}else if(StringUtils.isBlank(userModel.getPassword())) {
			errorMessage = "Please enter password";
			
			result = false;
		}
		
		return result;
	}

    public void onClickLogin() {
    	try {
    		if(!validate()) {
    			return;
    		}
    		
    		User user = userService.checkUserLogin(new User(userModel.getUserName(), userModel.getPassword()));
	    	
	    	if(user != null) {
	    		erpSession.setUser(user);
	    		
	    		FacesContext.getCurrentInstance().getExternalContext().redirect("cop_dashboard.jsf");
	    		
	    		errorMessage = null;
	    	}else {
	    		errorMessage = "username or password not correct";
	    	}
    	}catch (Exception e) {

    	}
    }

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
