package com.erp.dashboard.model;

import java.io.Serializable;
import java.math.BigDecimal;


public class InfCopReceiptTempChart implements Serializable {
	private static final long serialVersionUID = 12412415644L;
	
	private String branchType;
	private BigDecimal count;
	private BigDecimal amountHeader;
	
	public BigDecimal getCount() {
		return count;
	}
	public void setCount(BigDecimal count) {
		this.count = count;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}
	public BigDecimal getAmountHeader() {
		return amountHeader;
	}
	public void setAmountHeader(BigDecimal amountHeader) {
		this.amountHeader = amountHeader;
	}
}
