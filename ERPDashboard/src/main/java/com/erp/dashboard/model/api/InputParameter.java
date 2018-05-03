package com.erp.dashboard.model.api;

import java.util.List;

public class InputParameter {
	private List<InfCopReceiptTempAPI> revenue;
	private List<AgentServiceFee> agent_service_fee;

	public List<InfCopReceiptTempAPI> getRevenue() {
		return revenue;
	}

	public void setRevenue(List<InfCopReceiptTempAPI> revenue) {
		this.revenue = revenue;
	}

	public List<AgentServiceFee> getAgent_service_fee() {
		return agent_service_fee;
	}

	public void setAgent_service_fee(List<AgentServiceFee> agent_service_fee) {
		this.agent_service_fee = agent_service_fee;
	}
}
