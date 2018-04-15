package com.erp.dashboard.action;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.dashboard.ERPSession;
import com.erp.dashboard.model.InfCopReceiptTemp;
import com.erp.dashboard.model.InfCopReceiptTempChart;
import com.erp.dashboard.model.User;
import com.erp.dashboard.service.IERPService;
import com.erp.dashboard.utils.ERPUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@ManagedBean
@SessionScoped
public class DashboardNewAction {
	private User userLogin;
	private List<InfCopReceiptTempChart> ratings;
	private List<InfCopReceiptTemp> copReceiptTemps;
	private String receiptJSON;
	private String chartPS;
	private String datas;
	private String param;
	private Date date;
	
	@Autowired
	IERPService userService;
	
	@Autowired
	ERPSession erpSession;
	
	public void onload() {
		try {
			userLogin = erpSession.getUser();
			if(userLogin == null)
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
			
			datas = "[]";
			date = null;
			chartPS = "[]";
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateReceiptTempChart() {
		try {
			updateChart();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<InfCopReceiptTemp> getReceiptForSave() {
		List<InfCopReceiptTemp> receiptForSaves = new ArrayList<>();
		if(!ERPUtils.collectionIsEmpty(copReceiptTemps)){
			for(InfCopReceiptTemp copReceiptTemp : copReceiptTemps) {
				if(!copReceiptTemp.getArReceiptDate().equals(copReceiptTemp.getOldArReceiptDate())
						|| ("N".equalsIgnoreCase(copReceiptTemp.getStatus())
								&& !StringUtils.equalsIgnoreCase(copReceiptTemp.getOldStatus(), copReceiptTemp.getStatus()))) {
					receiptForSaves.add(copReceiptTemp);
				}
			}
		}
		return receiptForSaves;
	}
	
	public void saveReceiptTemp() {
		try {
			List<InfCopReceiptTemp> receiptForSaves = getReceiptForSave();
			
			if(!ERPUtils.collectionIsEmpty(receiptForSaves)){
				userService.saveReceiptTempDetails(receiptForSaves);
			}
			
			updateChart();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateRevenues() throws JsonProcessingException {
		if(param != null
				&& param != "") {
			copReceiptTemps = userService.getReceiptTempDetail(param, date);
			
			ObjectMapper mapper = new ObjectMapper();
			receiptJSON = mapper.writeValueAsString(copReceiptTemps);
			
			for(InfCopReceiptTemp copReceiptTemp :  copReceiptTemps) {
				System.out.println("ArReceiptDate :: " + copReceiptTemp.getArReceiptDate());
			}
			
			System.out.println("receiptJSON :: " + receiptJSON);
		}
	}
	
	public void updateChartPS() throws JsonProcessingException {
		if(param != null
				&& param != "") {
			ratings = userService.getReceiptTempParcelShop(date);
			
			ObjectMapper mapper = new ObjectMapper();
			chartPS = mapper.writeValueAsString(ratings);
		}
	}
	
	public void updateChart() throws JsonProcessingException {
		ratings = userService.getReceiptTempByType(date);
		
		ObjectMapper mapper = new ObjectMapper();
        datas = mapper.writeValueAsString(ratings);
        
        chartPS = "[]";
	}
	
	public void onLogout() {
		try {
			erpSession.setUser(null);
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public User getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(User userLogin) {
		this.userLogin = userLogin;
	}

	public String getDatas() {
		return datas;
	}

	public void setDatas(String datas) {
		this.datas = datas;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<InfCopReceiptTempChart> getRatings() {
		return ratings;
	}

	public void setRatings(List<InfCopReceiptTempChart> ratings) {
		this.ratings = ratings;
	}

	public IERPService getUserService() {
		return userService;
	}

	public void setUserService(IERPService userService) {
		this.userService = userService;
	}

	public ERPSession getErpSession() {
		return erpSession;
	}

	public void setErpSession(ERPSession erpSession) {
		this.erpSession = erpSession;
	}

	public List<InfCopReceiptTemp> getCopReceiptTemps() {
		return copReceiptTemps;
	}

	public void setCopReceiptTemps(List<InfCopReceiptTemp> copReceiptTemps) {
		this.copReceiptTemps = copReceiptTemps;
	}

	public String getChartPS() {
		return chartPS;
	}

	public void setChartPS(String chartPS) {
		this.chartPS = chartPS;
	}

	public String getReceiptJSON() {
		return receiptJSON;
	}

	public void setReceiptJSON(String receiptJSON) {
		this.receiptJSON = receiptJSON;
	}
}
