package com.erp.dashboard.model.api;

import java.math.BigDecimal;

public class AgentServiceFee{
	private String category_code;
    private String description;
    private BigDecimal quantity;
    private BigDecimal unit_price;
    private String transaction_date;
    private String vendor_code;
    private String vendor_site_code;
    private BigDecimal expense;
    private String cost_center;
    private BigDecimal group_id;
    private String ref_no;
    private String segmen1;
    
	public String getCategory_code() {
		return category_code;
	}
	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(BigDecimal unit_price) {
		this.unit_price = unit_price;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public String getVendor_code() {
		return vendor_code;
	}
	public void setVendor_code(String vendor_code) {
		this.vendor_code = vendor_code;
	}
	public String getVendor_site_code() {
		return vendor_site_code;
	}
	public void setVendor_site_code(String vendor_site_code) {
		this.vendor_site_code = vendor_site_code;
	}
	public BigDecimal getExpense() {
		return expense;
	}
	public void setExpense(BigDecimal expense) {
		this.expense = expense;
	}
	public String getCost_center() {
		return cost_center;
	}
	public void setCost_center(String cost_center) {
		this.cost_center = cost_center;
	}
	public BigDecimal getGroup_id() {
		return group_id;
	}
	public void setGroup_id(BigDecimal group_id) {
		this.group_id = group_id;
	}
	public String getRef_no() {
		return ref_no;
	}
	public void setRef_no(String ref_no) {
		this.ref_no = ref_no;
	}
	public String getSegmen1() {
		return segmen1;
	}
	public void setSegmen1(String segmen1) {
		this.segmen1 = segmen1;
	}
}