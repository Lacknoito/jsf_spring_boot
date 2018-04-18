package com.erp.dashboard.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.internal.BinaryStreamImpl;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

@Component
@ManagedBean
@RequestScoped
public class FormView {
	private StreamedContent streamedContent;

	@PostConstruct
	public void init() throws Exception {

	}

	public StreamedContent getStreamedContent() {
		return streamedContent;
	}

	public void setStreamedContent(StreamedContent streamedContent) {
		this.streamedContent = streamedContent;
	}

	private StreamedContent createStream(String fileName) throws Exception {
		streamedContent = new DefaultStreamedContent(getData(fileName), "application/pdf", "downloaded_" + fileName);
		return streamedContent;
	}
	
	public void showPDF() throws Exception {
		JasperReport jasperReport = JasperCompileManager.compileReport("src/main/webapp/report/report.jrxml");
		
		String dbUrl = "jdbc:oracle:thin:@//THBKK01ITSN127.kerrylogistics.com:1521/XE";
		String dbDriver = "org.hibernate.dialect.Oracle10gDialect";
		String dbUname = "SYSTEM";
		String dbPwd = "password";
		Class.forName(dbDriver);
		Connection conn = DriverManager.getConnection(dbUrl, dbUname, dbPwd);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("RECEIPT_DATE", "10/04/2018");
		
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/xls");
		response.setHeader("Content-disposition", "attachment; filename=" + "test" + ".xls");
		OutputStream out = response.getOutputStream();
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
		JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out);
        exporter.exportReport();
        FacesContext.getCurrentInstance().responseComplete();
	}
	
//	public void showPDF() throws Exception {
////		createStream("KETH_AR_Receipt_Voucher_Region_170418.pdf");
//
//		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//		response.setContentType("application/xls");
//        response.setHeader("Content-disposition", "attachment; filename=" + "test" + ".xls");
//        
//		JasperReport jasperReport = JasperCompileManager.compileReport("src/main/webapp/report/report.jrxml");
//		
//		String dbUrl = "jdbc:oracle:thin:@//THBKK01ITSN127.kerrylogistics.com:1521/XE";
//		String dbDriver = "org.hibernate.dialect.Oracle10gDialect";
//		String dbUname = "SYSTEM";
//		String dbPwd = "password";
//		Class.forName(dbDriver);
//		Connection conn = DriverManager.getConnection(dbUrl, dbUname, dbPwd);
//		
//		Map<String, Object> parameters = new HashMap<String, Object>();
//		parameters.put("RECEIPT_DATE", "11/04/2018");
//		
//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
//		JRXlsExporter exporter = new JRXlsExporter();
//		exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME, jasperPrint);
//		FacesContext.getCurrentInstance().responseComplete();
//	}
	
	public InputStream genJasper() throws Exception {
		JasperReport jasperReport = JasperCompileManager.compileReport("src/main/webapp/report/report.jrxml");
		
		String dbUrl = "jdbc:oracle:thin:@//THBKK01ITSN127.kerrylogistics.com:1521/XE";
		String dbDriver = "org.hibernate.dialect.Oracle10gDialect";
		String dbUname = "SYSTEM";
		String dbPwd = "password";
		Class.forName(dbDriver);
		Connection conn = DriverManager.getConnection(dbUrl, dbUname, dbPwd);
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,new HashMap(), conn);
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		
		InputStream stream = new BinaryStreamImpl(bytes);
		
		return stream;
	}

	private InputStream getData(String fileName) {

		// pdf files under src\main\resources
		File file = new File("C:/Users/NNoito/Desktop/ERP/" + fileName);

		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return is;

	}

	public String generateRandomIdForNotCaching() {
		return java.util.UUID.randomUUID().toString();
	}
}
