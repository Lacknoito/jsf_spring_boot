package com.erp.dashboard.dao;

import java.util.List;

import com.erp.dashboard.model.api.AgentServiceFee;

public interface IPRCommissionTempDao {
	public void insertPRCommissionTempList(List<AgentServiceFee> agentServiceFees);
}
