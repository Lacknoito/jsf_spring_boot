package com.erp.dashboard.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.erp.dashboard.model.InfCopReceiptTemp;
import com.erp.dashboard.service.IERPService;
import com.erp.dashboard.utils.ERPUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Component
@ManagedBean
@RequestScoped
public class ShowAction {
	@Autowired
	IERPService userService;
	
	@Autowired
	ApplicationContext context;
	
	private String text;
	private boolean bool;
	
	private DefaultTableModel tableModel;
	
	private List<InfCopReceiptTemp> copReceiptTemps;
	
	public void updateReceiptTempChart() {
		try {
			System.out.println("text :: " + text);
			System.out.println("bool :: " + bool);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void onload() throws JsonProcessingException {
		System.out.println("onload");
		
		text = "Test";
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 9);
		copReceiptTemps = userService.getReceiptTempDetail("BSD", calendar.getTime());
		
		for(InfCopReceiptTemp s : copReceiptTemps) {
//			s.setArReceiptDate(new Date());
			s.setArReceiptDateStr(ERPUtils.convertDateToStringFormat(s.getArReceiptDate(), ERPUtils.SIMPLE_DATE_FORMAT));
		}
		
		System.out.println(copReceiptTemps.size());
	}
	
	public void genPDF() throws JRException, IOException {
		JasperReport jasperReport = JasperCompileManager
	               .compileReport("D:/workspace/Report/report.jrxml");
		
		  // Parameters for report
	       Map<String, Object> parameters = new HashMap<String, Object>();
	 
	       // DataSource
	       // This is simple example, no database.
	       // then using empty datasource.
	       JRDataSource dataSource = new JREmptyDataSource();
	 
	       JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
	               parameters, dataSource);
	 
	       // Export to PDF.
	       JasperExportManager.exportReportToPdfFile(jasperPrint,
	               "D:/workspace/Report/StyledTextReport.pdf");
	        
//	       File pdf = File.createTempFile("output.", ".pdf");
//	       JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(pdf));
	       
	       System.out.println("Done!");
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}
}
