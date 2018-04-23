package com.erp.dashboard.entity;

import java.math.BigDecimal;

public class Accounting {
	private String RECEIPT_NUMBER;
	private String ACCOUNT_CODE;
	private String ACCOUNT_NAME;
	private BigDecimal ACCOUNTED_DR;
	private BigDecimal ACCOUNTED_CR;
	private String COST_CENTER;
	
	public String getRECEIPT_NUMBER() {
		return RECEIPT_NUMBER;
	}
	public void setRECEIPT_NUMBER(String rECEIPT_NUMBER) {
		RECEIPT_NUMBER = rECEIPT_NUMBER;
	}
	public String getACCOUNT_CODE() {
		return ACCOUNT_CODE;
	}
	public void setACCOUNT_CODE(String aCCOUNT_CODE) {
		ACCOUNT_CODE = aCCOUNT_CODE;
	}
	public String getACCOUNT_NAME() {
		return ACCOUNT_NAME;
	}
	public void setACCOUNT_NAME(String aCCOUNT_NAME) {
		ACCOUNT_NAME = aCCOUNT_NAME;
	}
	public BigDecimal getACCOUNTED_DR() {
		return ACCOUNTED_DR;
	}
	public void setACCOUNTED_DR(BigDecimal aCCOUNTED_DR) {
		ACCOUNTED_DR = aCCOUNTED_DR;
	}
	public BigDecimal getACCOUNTED_CR() {
		return ACCOUNTED_CR;
	}
	public void setACCOUNTED_CR(BigDecimal aCCOUNTED_CR) {
		ACCOUNTED_CR = aCCOUNTED_CR;
	}
	public String getCOST_CENTER() {
		return COST_CENTER;
	}
	public void setCOST_CENTER(String cOST_CENTER) {
		COST_CENTER = cOST_CENTER;
	}
}
