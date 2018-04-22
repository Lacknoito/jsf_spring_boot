package com.erp.dashboard.model.api;

import java.io.Serializable;
import java.math.BigDecimal;

public class InfCopReceiptTempAPI implements Serializable {
	private static final long serialVersionUID = 212412234L;

	private String ar_receipt_batch;
	private String ar_receipt_number;
	private String ar_receipt_date;
	private String ar_gl_date;
	private BigDecimal ar_amount_header;
	private String ar_comments_header;
	private String ar_pos_type;
	private BigDecimal ar_amount_dis;
	private String ar_comments_line;
	private String ar_shop_name;
	private String ar_source;
	
	public String getAr_receipt_batch() {
		return ar_receipt_batch;
	}
	public void setAr_receipt_batch(String ar_receipt_batch) {
		this.ar_receipt_batch = ar_receipt_batch;
	}
	public String getAr_receipt_number() {
		return ar_receipt_number;
	}
	public void setAr_receipt_number(String ar_receipt_number) {
		this.ar_receipt_number = ar_receipt_number;
	}
	public String getAr_receipt_date() {
		return ar_receipt_date;
	}
	public void setAr_receipt_date(String ar_receipt_date) {
		this.ar_receipt_date = ar_receipt_date;
	}
	public String getAr_gl_date() {
		return ar_gl_date;
	}
	public void setAr_gl_date(String ar_gl_date) {
		this.ar_gl_date = ar_gl_date;
	}
	public BigDecimal getAr_amount_header() {
		return ar_amount_header;
	}
	public void setAr_amount_header(BigDecimal ar_amount_header) {
		this.ar_amount_header = ar_amount_header;
	}
	public String getAr_comments_header() {
		return ar_comments_header;
	}
	public void setAr_comments_header(String ar_comments_header) {
		this.ar_comments_header = ar_comments_header;
	}
	public String getAr_pos_type() {
		return ar_pos_type;
	}
	public void setAr_pos_type(String ar_pos_type) {
		this.ar_pos_type = ar_pos_type;
	}
	public BigDecimal getAr_amount_dis() {
		return ar_amount_dis;
	}
	public void setAr_amount_dis(BigDecimal ar_amount_dis) {
		this.ar_amount_dis = ar_amount_dis;
	}
	public String getAr_comments_line() {
		return ar_comments_line;
	}
	public void setAr_comments_line(String ar_comments_line) {
		this.ar_comments_line = ar_comments_line;
	}
	public String getAr_shop_name() {
		return ar_shop_name;
	}
	public void setAr_shop_name(String ar_shop_name) {
		this.ar_shop_name = ar_shop_name;
	}
	public String getAr_source() {
		return ar_source;
	}
	public void setAr_source(String ar_source) {
		this.ar_source = ar_source;
	}
}
