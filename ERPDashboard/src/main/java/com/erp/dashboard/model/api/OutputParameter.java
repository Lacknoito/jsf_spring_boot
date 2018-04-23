package com.erp.dashboard.model.api;

import java.io.Serializable;

public class OutputParameter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 123123121L;

	private String cost_center;
	private String status_code;
	private String status_desc;
	
	public String getStatus_code() {
		return status_code;
	}
	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
	public String getStatus_desc() {
		return status_desc;
	}
	public void setStatus_desc(String status_desc) {
		this.status_desc = status_desc;
	}
	public String getCost_center() {
		return cost_center;
	}
	public void setCost_center(String cost_center) {
		this.cost_center = cost_center;
	}
}
