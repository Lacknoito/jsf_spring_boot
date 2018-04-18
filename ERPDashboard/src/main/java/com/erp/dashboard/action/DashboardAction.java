package com.erp.dashboard.action;


import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.engine.jdbc.internal.BinaryStreamImpl;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
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

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

@SuppressWarnings("deprecation")
@Component
@ManagedBean
@SessionScoped
public class DashboardAction {
	private User userLogin;
	private List<InfCopReceiptTempChart> ratings;
	private List<InfCopReceiptTemp> copReceiptTemps;
	private String chartPS;
	private String datas;
	private String dateStr;
	private StreamedContent streamedContent;
	

	private String param;
	private String param2;
	
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
				copReceiptTemp.setArReceiptDate(ERPUtils.convertStringToDateFormat(copReceiptTemp.getArReceiptDateStr(), ERPUtils.SIMPLE_DATE_FORMAT));
				if(!copReceiptTemp.getArReceiptDate().equals(copReceiptTemp.getOldArReceiptDate())
						|| ("N".equalsIgnoreCase(copReceiptTemp.getStatus())
								&& !StringUtils.equalsIgnoreCase(copReceiptTemp.getOldStatus(), copReceiptTemp.getStatus()))) {
					receiptForSaves.add(copReceiptTemp);
				}
			}
		}
		return receiptForSaves;
	}
	
	public void updateStatus() {
		for(InfCopReceiptTemp copReceiptTemp : copReceiptTemps) {
			if(copReceiptTemp.getRownum().toString().equals(param)) {
				if("true".equalsIgnoreCase(param2)) {
					copReceiptTemp.setStatus(copReceiptTemp.getOldStatus());
				}else {
					copReceiptTemp.setStatus("N");
				}
				
				break;
			}
		}
	}
	
	public void saveReceiptTemp() {
		try {
			List<InfCopReceiptTemp> receiptForSaves = getReceiptForSave();
			
			System.out.println(receiptForSaves.size());
			
			if(!ERPUtils.collectionIsEmpty(receiptForSaves)){
				userService.saveReceiptTempDetails(receiptForSaves);
			}
			
			updateChart();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateReceiptTemp() throws JsonProcessingException {
		if(param != null
				&& param != "") {
			copReceiptTemps = userService.getReceiptTempDetail(param, ERPUtils.convertStringToDateFormat(dateStr, ERPUtils.SIMPLE_DATE_FORMAT));
			
			int index = 1;
			for(InfCopReceiptTemp copReceiptTemp :  copReceiptTemps) {
				copReceiptTemp.setRownum(index++);
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
			
			System.out.println("chartPS :: " + chartPS);
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
	
	public void showPDF() throws Exception {
		if(StringUtils.isNotBlank(dateStr)) {
			createStream("report.pdf");
		}
	}
	
	private StreamedContent createStream(String fileName) throws Exception {
		streamedContent = new DefaultStreamedContent(genJasper(), "application/pdf", "downloaded_" + fileName);
		return streamedContent;
	}
	
	public InputStream genJasper() throws Exception {
		JasperReport jasperReport = JasperCompileManager.compileReport("src/main/webapp/report/report.jrxml");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("RECEIPT_DATE", dateStr);
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, genConnection());
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		
		InputStream stream = new BinaryStreamImpl(bytes);
		
		return stream;
	}
	
	public void exportExcel() throws Exception {
		JasperReport jasperReport = JasperCompileManager.compileReport("src/main/webapp/report/report_excel.jrxml");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("RECEIPT_DATE", dateStr);
		
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/xls");
		response.setHeader("Content-disposition", "attachment; filename=" + "test" + ".xlsx");
		OutputStream out = response.getOutputStream();
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, genConnection());
		JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out);
        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
        exporter.exportReport();
        FacesContext.getCurrentInstance().responseComplete();
	}
	
	public Connection genConnection() throws Exception {
		String dbUrl = "jdbc:oracle:thin:@//THBKK01ITSN127.kerrylogistics.com:1521/XE";
		String dbDriver = "org.hibernate.dialect.Oracle10gDialect";
		String dbUname = "SYSTEM";
		String dbPwd = "password";
		Class.forName(dbDriver);
		Connection conn = DriverManager.getConnection(dbUrl, dbUname, dbPwd);
		
		return conn;
	}
	
	public void exportTransactionExcel() throws Exception {
		JasperReport jasperReport = JasperCompileManager.compileReport("src/main/webapp/report/transaction_excel.jrxml");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("RECEIPT_DATE", dateStr);
		
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/xls");
		response.setHeader("Content-disposition", "attachment; filename=" + "test" + ".xlsx");
		OutputStream out = response.getOutputStream();
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(copReceiptTemps));
		JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out);
        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
        exporter.exportReport();
        FacesContext.getCurrentInstance().responseComplete();
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

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}
	public StreamedContent getStreamedContent() {
		return streamedContent;
	}

	public void setStreamedContent(StreamedContent streamedContent) {
		this.streamedContent = streamedContent;
	}
}
