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
	private String arGlDateStr;
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
	private BigDecimal freight;
	private BigDecimal cod;
	private BigDecimal packageSalePackage;
	private BigDecimal topup;
	private BigDecimal discount;
	private BigDecimal bankCharge;
	private BigDecimal creditCard;
	private BigDecimal adjLinePay;
	private BigDecimal vat;
	
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
	public BigDecimal getFreight() {
		return freight;
	}
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	public BigDecimal getCod() {
		return cod;
	}
	public void setCod(BigDecimal cod) {
		this.cod = cod;
	}
	public BigDecimal getPackageSalePackage() {
		return packageSalePackage;
	}
	public void setPackageSalePackage(BigDecimal packageSalePackage) {
		this.packageSalePackage = packageSalePackage;
	}
	public BigDecimal getTopup() {
		return topup;
	}
	public void setTopup(BigDecimal topup) {
		this.topup = topup;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getBankCharge() {
		return bankCharge;
	}
	public void setBankCharge(BigDecimal bankCharge) {
		this.bankCharge = bankCharge;
	}
	public BigDecimal getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(BigDecimal creditCard) {
		this.creditCard = creditCard;
	}
	public BigDecimal getAdjLinePay() {
		return adjLinePay;
	}
	public void setAdjLinePay(BigDecimal adjLinePay) {
		this.adjLinePay = adjLinePay;
	}
	public BigDecimal getVat() {
		return vat;
	}
	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}
	public String getArGlDateStr() {
		return arGlDateStr;
	}
	public void setArGlDateStr(String arGlDateStr) {
		this.arGlDateStr = arGlDateStr;
	}
}
