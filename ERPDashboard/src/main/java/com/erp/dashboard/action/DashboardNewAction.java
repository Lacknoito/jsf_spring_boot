package com.erp.dashboard.action;


import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
	private String chartPS;
	private String datas;
	private String param;
	private String dateStr;
	
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
			dateStr = null;
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
//				if(!copReceiptTemp.getArReceiptDate().equals(copReceiptTemp.getOldArReceiptDate())
//						|| ("N".equalsIgnoreCase(copReceiptTemp.getStatus())
//								&& !StringUtils.equalsIgnoreCase(copReceiptTemp.getOldStatus(), copReceiptTemp.getStatus()))) {
					copReceiptTemp.setArReceiptDate(ERPUtils.convertStringToDateFormat(copReceiptTemp.getArReceiptDateStr(), ERPUtils.SIMPLE_DATE_FORMAT));
					receiptForSaves.add(copReceiptTemp);
//				}
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
			copReceiptTemps = userService.getReceiptTempDetail(param, ERPUtils.convertStringToDateFormat(dateStr, ERPUtils.SIMPLE_DATE_FORMAT));
			
			for(InfCopReceiptTemp copReceiptTemp :  copReceiptTemps) {
				copReceiptTemp.setArReceiptDateStr(ERPUtils.convertDateToStringFormat(copReceiptTemp.getArReceiptDate(), ERPUtils.SIMPLE_DATE_FORMAT));
			}
		}
	}
	
	public void updateChartPS() throws JsonProcessingException {
		if(param != null
				&& param != "") {
			ratings = userService.getReceiptTempParcelShop(ERPUtils.convertStringToDateFormat(dateStr, ERPUtils.SIMPLE_DATE_FORMAT));
			
			ObjectMapper mapper = new ObjectMapper();
			chartPS = mapper.writeValueAsString(ratings);
		}
	}
	
	public void updateChart() throws JsonProcessingException {
		ratings = userService.getReceiptTempByType(ERPUtils.convertStringToDateFormat(dateStr, ERPUtils.SIMPLE_DATE_FORMAT));
		
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

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
}
