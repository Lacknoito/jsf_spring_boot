package com.erp.dashboard.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InfCopReceiptTemp implements Serializable {
	private static final long serialVersionUID = 21251235234L;

	private Integer rownum;
	private String shopCode;
	private String shopName;
	private String arReceiptDateStr;
	private Date arReceiptDate;
	private Date oldArReceiptDate;
	private BigDecimal amountHeader;
	private BigDecimal cash;
	private BigDecimal rabit;
	private BigDecimal creditBBL;
	private BigDecimal creditSCB;
	private BigDecimal linePay;
	private BigDecimal bonus;
	private BigDecimal adjOther;
	private BigDecimal adjReturnCharge;
	private BigDecimal suspense;
	private BigDecimal withholdingTax;
	private BigDecimal promotion;
	private String status;
	private String oldStatus;
	private BigDecimal insurance;
	
	public Integer getRownum() {
		return rownum;
	}
	public void setRownum(Integer rownum) {
		this.rownum = rownum;
	}
	public String getShopCode() {
		return shopCode;
	}
	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Date getArReceiptDate() {
		return arReceiptDate;
	}
	public void setArReceiptDate(Date arReceiptDate) {
		this.arReceiptDate = arReceiptDate;
	}
	public BigDecimal getCash() {
		return cash;
	}
	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}
	public BigDecimal getRabit() {
		return rabit;
	}
	public void setRabit(BigDecimal rabit) {
		this.rabit = rabit;
	}
	public BigDecimal getCreditBBL() {
		return creditBBL;
	}
	public void setCreditBBL(BigDecimal creditBBL) {
		this.creditBBL = creditBBL;
	}
	public BigDecimal getCreditSCB() {
		return creditSCB;
	}
	public void setCreditSCB(BigDecimal creditSCB) {
		this.creditSCB = creditSCB;
	}
	public BigDecimal getLinePay() {
		return linePay;
	}
	public void setLinePay(BigDecimal linePay) {
		this.linePay = linePay;
	}
	public BigDecimal getBonus() {
		return bonus;
	}
	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}
	public BigDecimal getAdjOther() {
		return adjOther;
	}
	public void setAdjOther(BigDecimal adjOther) {
		this.adjOther = adjOther;
	}
	public BigDecimal getAdjReturnCharge() {
		return adjReturnCharge;
	}
	public void setAdjReturnCharge(BigDecimal adjReturnCharge) {
		this.adjReturnCharge = adjReturnCharge;
	}
	public BigDecimal getSuspense() {
		return suspense;
	}
	public void setSuspense(BigDecimal suspense) {
		this.suspense = suspense;
	}
	public BigDecimal getWithholdingTax() {
		return withholdingTax;
	}
	public void setWithholdingTax(BigDecimal withholdingTax) {
		this.withholdingTax = withholdingTax;
	}
	public BigDecimal getPromotion() {
		return promotion;
	}
	public void setPromotion(BigDecimal promotion) {
		this.promotion = promotion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BigDecimal getAmountHeader() {
		return amountHeader;
	}
	public void setAmountHeader(BigDecimal amountHeader) {
		this.amountHeader = amountHeader;
	}
	public Date getOldArReceiptDate() {
		return oldArReceiptDate;
	}
	public void setOldArReceiptDate(Date oldArReceiptDate) {
		this.oldArReceiptDate = oldArReceiptDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getInsurance() {
		return insurance;
	}
	public void setInsurance(BigDecimal insurance) {
		this.insurance = insurance;
	}
	public String getOldStatus() {
		return oldStatus;
	}
	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}
	public String getArReceiptDateStr() {
		return arReceiptDateStr;
	}
	public void setArReceiptDateStr(String arReceiptDateStr) {
		this.arReceiptDateStr = arReceiptDateStr;
	}
}
