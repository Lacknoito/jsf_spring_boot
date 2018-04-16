package com.erp.dashboard.action;

import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.dashboard.model.InfCopReceiptTemp;
import com.erp.dashboard.service.IERPService;
import com.erp.dashboard.utils.ERPUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
@ManagedBean
@RequestScoped
public class ShowAction {
	@Autowired
	IERPService userService;
	
	private List<InfCopReceiptTemp> copReceiptTemps;
	
	public void onload() throws JsonProcessingException {
		System.out.println("onload");
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 9);
		copReceiptTemps = userService.getReceiptTempDetail("BSD", calendar.getTime());
		
		for(InfCopReceiptTemp s : copReceiptTemps) {
//			s.setArReceiptDate(new Date());
			s.setArReceiptDateStr(ERPUtils.convertDateToStringFormat(s.getArReceiptDate(), ERPUtils.SIMPLE_DATE_FORMAT));
		}
		
		System.out.println(copReceiptTemps.size());
	}
	
	public void saveReceiptTemp() {
		try {
			System.out.println("---saveReceiptTemp---");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<InfCopReceiptTemp> getCopReceiptTemps() {
		return copReceiptTemps;
	}

	public void setCopReceiptTemps(List<InfCopReceiptTemp> copReceiptTemps) {
		this.copReceiptTemps = copReceiptTemps;
	}
}
