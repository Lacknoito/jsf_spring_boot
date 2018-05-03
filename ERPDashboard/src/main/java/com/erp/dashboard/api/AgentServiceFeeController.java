package com.erp.dashboard.api;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erp.dashboard.model.api.AgentServiceFee;
import com.erp.dashboard.model.api.InputParameter;
import com.erp.dashboard.model.api.OutputParameterReceiptTemp;
import com.erp.dashboard.service.IERPService;
import com.erp.dashboard.utils.ERPUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AgentServiceFeeController {
	@Autowired
	private IERPService erpService; 

	private static Logger logger = LogManager.getLogger(AgentServiceFeeController.class);
	
	ObjectMapper mapper = new ObjectMapper();
	
	public boolean validateRequireField(InputParameter inputParameter) {
		if(inputParameter == null) {
			return false;
		}else if(ERPUtils.collectionIsEmpty(inputParameter.getAgent_service_fee())) {
			return false;
		}else {
			for(AgentServiceFee param : inputParameter.getAgent_service_fee()) {

			}
		}
		
		return true;
	}
	
	@PostMapping("/agent_service_fee")
	public OutputParameterReceiptTemp postRevenueData(@RequestBody InputParameter agentServiceFee) {
		OutputParameterReceiptTemp outputParameter = new OutputParameterReceiptTemp();
		try {
			logger.info(mapper.writeValueAsString(agentServiceFee));
			
			if(!validateRequireField(agentServiceFee)) {
				outputParameter.setStatus_code("100");
				outputParameter.setStatus_desc("Require Information Parameter");
			}else {
				erpService.insertPRCommissionTempList(agentServiceFee.getAgent_service_fee());
				
				outputParameter.setStatus_code("000");
				outputParameter.setStatus_desc("Success Requisition");
			}
		} catch (Exception e) {
			outputParameter.setStatus_code("999");
			outputParameter.setStatus_desc(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		
		return outputParameter;
	}
}
