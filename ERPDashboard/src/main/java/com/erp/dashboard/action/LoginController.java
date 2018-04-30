package com.erp.dashboard.action;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.erp.dashboard.ERPSession;
import com.erp.dashboard.entity.User;
import com.erp.dashboard.model.UserModel;
import com.erp.dashboard.service.IERPService;
import com.erp.dashboard.utils.SessionUtils;

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

    public void onClickLogin() {
    	try {
    		User user = userService.checkUserLogin(new User(userModel.getUserName(), userModel.getPassword()));
	    	
	    	if(user != null) {
	    		userLogin = new User();
	    		userLogin.setUserName("test");
	    		
	    		erpSession.setUser(user);
	    		
	    		SessionUtils.setUserName("ERPTest");
	    		
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
