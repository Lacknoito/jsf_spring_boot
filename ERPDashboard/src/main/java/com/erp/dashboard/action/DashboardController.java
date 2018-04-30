package com.erp.dashboard.action;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.erp.dashboard.ERPSession;

@RequestScope
@Component(value = "dashboardController")
public class DashboardController {
	private static Logger logger = LogManager.getLogger(DashboardController.class);
	
	@Autowired
	private ERPSession erpSession;
	
	public void onPageCopDashboard() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("cop_dashboard.jsf");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void onLogout() {
		try {
			erpSession.setUser(null);
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
