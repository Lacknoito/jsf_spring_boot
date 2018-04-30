package com.erp.dashboard.action;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.engine.jdbc.internal.BinaryStreamImpl;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.erp.dashboard.ERPSession;
import com.erp.dashboard.entity.Accounting;
import com.erp.dashboard.entity.User;
import com.erp.dashboard.model.InfCopReceiptTemp;
import com.erp.dashboard.model.InfCopReceiptTempChart;
import com.erp.dashboard.service.IERPService;
import com.erp.dashboard.utils.ERPUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

@SessionScope
@Component(value = "copDashboardController")
public class COPDashboardController {
	private User userLogin;
	private List<InfCopReceiptTempChart> ratings;
	private List<InfCopReceiptTemp> copReceiptTemps;
	private String chartPS;
	private String datas;
	private String dateStr;
	private StreamedContent streamedContent;
	private List<Accounting> accountings;
	private boolean result;
	
	private static Logger logger = LogManager.getLogger(COPDashboardController.class);
	
	@Autowired
	DataSource dataSource;

	private String param;
	private String param2;
	
	@Autowired
	IERPService erpService;
	
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
			logger.error(e.getMessage(), e);
		}
	}
	
	public void updateReceiptTempChart() {
		try {
			updateChart();
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ""));
			logger.error(e.getMessage(), e);
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
			
			if(!ERPUtils.collectionIsEmpty(receiptForSaves)){
				erpService.saveReceiptTempDetails(receiptForSaves);
			}
			
			updateChart();
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void updateReceiptTemp() throws JsonProcessingException {
		if(param != null
				&& param != "") {
			copReceiptTemps = erpService.getReceiptTempDetail(param, ERPUtils.convertStringToDateFormat(dateStr, ERPUtils.SIMPLE_DATE_FORMAT));
			
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
			ratings = erpService.getReceiptTempParcelShop(ERPUtils.convertStringToDateFormat(dateStr, ERPUtils.SIMPLE_DATE_FORMAT));
			
			ObjectMapper mapper = new ObjectMapper();
			chartPS = mapper.writeValueAsString(ratings);
		}
	}
	
	public void updateChart() throws JsonProcessingException {
		if(StringUtils.isBlank(dateStr)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Please input receipt date."));
			
			datas = "[]";
			
			return;
		}
		
		ratings = erpService.getReceiptTempByType(ERPUtils.convertStringToDateFormat(dateStr, ERPUtils.SIMPLE_DATE_FORMAT));
		
		if(ERPUtils.collectionIsEmpty(ratings)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Data not available."));
		}
		
		ObjectMapper mapper = new ObjectMapper();
        datas = mapper.writeValueAsString(ratings);
        
        chartPS = "[]";
	}
	
	
	public void showPDF() throws Exception {
		result = false;
		
		if(StringUtils.isBlank(dateStr)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Please input receipt date."));
			return;
		}
		
		accountings = erpService.queryAccountingByDate(dateStr);
		
		streamedContent = new DefaultStreamedContent(genJasper(), "application/pdf", "downloaded_report.pdf");
		
		result = true;
	}
	
//	public Connection genConnection() throws Exception {
//		String dbUrl = "jdbc:oracle:thin:@//172.25.32.77:1521/PROD";
//		String dbDriver = "org.hibernate.dialect.Oracle10gDialect";
//		String dbUname = "apps";
//		String dbPwd = "apps";
//		Class.forName(dbDriver);
//		Connection conn = DriverManager.getConnection(dbUrl, dbUname, dbPwd);
//		
//		return conn;
//	}
	
	public StreamedContent getShowExcelGL() throws Exception {
		if(StringUtils.isBlank(dateStr)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Please input receipt date."));
			return null;
		}
		
		String fullPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/report/report_gl.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(fullPath);
		
		Date date = ERPUtils.convertStringToDateFormat(dateStr, ERPUtils.SIMPLE_DATE_FORMAT);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		String monthStr;
		int month = ((int) calendar.get(Calendar.MONTH) + 1);
		if(month < 10) {
			monthStr = "0" + month;
		}else {
			monthStr = "" + month;
		}
		
		String period = calendar.get(Calendar.YEAR) + "00" + monthStr;

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("RECEIPT_DATE", dateStr);
		parameters.put("PERIOD_NO", period);
		
		logger.info("dateStr : " + dateStr);
		logger.info("period : " + period);
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
		JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
        exporter.exportReport();
        
        InputStream in = new BinaryStreamImpl(output.toByteArray());
        
        return new DefaultStreamedContent(in, "application/xls", "gl_accounting.xlsx");
	}
	
//	public void showExcelGL() throws Exception {
//		logger.info("showExcelGL start");
//		
//		result = false;
//		
//		if(StringUtils.isBlank(dateStr)) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Please input receipt date."));
//			return;
//		}
//		
//		String fullPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/report/report_gl.jrxml");
//		JasperReport jasperReport = JasperCompileManager.compileReport(fullPath);
//		
//		Map<String, Object> parameters = new HashMap<String, Object>();
//		parameters.put("RECEIPT_DATE", dateStr);
//		
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//		response.setContentType("application/xls");
//		response.setHeader("Content-disposition", "attachment; filename=" + "test" + ".xlsx");
//		OutputStream out = response.getOutputStream();
//		
//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, genConnection());
//		JRXlsxExporter exporter = new JRXlsxExporter();
//        exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
//        exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out);
//        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
//        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
//        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
//        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
//        exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
//        exporter.exportReport();
//        FacesContext.getCurrentInstance().responseComplete();
//		
//		result = true;
//		
//		logger.info("showExcelGL end");
//	}
	
	public InputStream genJasper() throws Exception {
		String fullPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/report/report.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(fullPath);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("RECEIPT_DATE", dateStr);
		
		DefaultJasperReportsContext context = DefaultJasperReportsContext.getInstance();
		JRPropertiesUtil.getInstance(context).setProperty("net.sf.jasperreports.xpath.executer.factory",
		    "net.sf.jasperreports.engine.util.xml.JaxenXPathExecuterFactory");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(accountings));
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		
		InputStream stream = new BinaryStreamImpl(bytes);
		
		return stream;
	}
	
	public void exportExcel() throws Exception {
		String fullPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/report/report_excel.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(fullPath);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("RECEIPT_DATE", dateStr);
		
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/xls");
		response.setHeader("Content-disposition", "attachment; filename=" + "temp_accounting" + ".xlsx");
		OutputStream out = response.getOutputStream();
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(accountings));
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
	
	public void exportTransactionExcel() throws Exception {
		String fullPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/report/transaction_excel.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(fullPath);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("RECEIPT_DATE", dateStr);
		
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/xls");
		response.setHeader("Content-disposition", "attachment; filename=" + "temp_data" + ".xlsx");
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

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public IERPService getErpService() {
		return erpService;
	}

	public void setErpService(IERPService erpService) {
		this.erpService = erpService;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
}
