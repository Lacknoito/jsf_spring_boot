package com.erp.dashboard.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component(value = "basicDocumentViewerController")
public class BasicDocumentViewerController implements Serializable {

	private static final long serialVersionUID = 1L;

    private StreamedContent content;

	public void onPrerender() {

		try {
	
			File file = new File("C:\\Users\\NNoito\\Desktop\\ERP\\KETH_AR_Receipt_Voucher_Region_170418.pdf");
			content = new DefaultStreamedContent(new FileInputStream(file), "application/pdf");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StreamedContent getContent() {
		return content;
	}

	public void setContent(StreamedContent content) {
		this.content = content;
	}
}
