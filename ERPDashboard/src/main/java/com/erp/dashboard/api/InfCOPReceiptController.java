package com.erp.dashboard.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erp.dashboard.model.api.InputParameter;
import com.erp.dashboard.model.api.OutputParameter;
import com.erp.dashboard.service.IERPService;
import com.erp.dashboard.utils.ERPUtils;

@RestController
public class InfCOPReceiptController {
	@Autowired
	private IERPService erpService; 
	
	private static Logger logger = LogManager.getLogger(InfCOPReceiptController.class);
	
	@PostMapping("/revenue_data")
	public OutputParameter postRevenueData(@RequestBody InputParameter inputParameter) {
		OutputParameter outputParameter = new OutputParameter();
		try {
			erpService.insertInfCOPReceiptTemp(inputParameter.getRevenue());
			
			outputParameter.setStatus_code("000");
			outputParameter.setStatus_desc("Success Requisition");
		} catch (Exception e) {
			outputParameter.setStatus_code("999");
			outputParameter.setStatus_desc(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		
		return outputParameter;
	}
	
	@GetMapping("/cost_center/{name}")
	public OutputParameter getCostCenter(@PathVariable("name") String name) {
		OutputParameter outputParameter = new OutputParameter();
		try {
			logger.info("InfCOPReceiptController getCostCenter name : " + name);
			
			List<String> costCenters = erpService.queryCostCenterByName(name);
			
			if(!ERPUtils.collectionIsEmpty(costCenters)) {
				if(costCenters.size() == 1) {
					outputParameter.setCost_center(costCenters.get(0));
					outputParameter.setStatus_code("000");
					outputParameter.setStatus_desc("Success Requisition");
				}else {
					outputParameter.setStatus_code("002");
				}
			}else {
				outputParameter.setStatus_code("001");
				outputParameter.setStatus_desc("Data not found");
			}
		} catch (Exception e) {
			outputParameter.setStatus_code("999");
			outputParameter.setStatus_desc(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		
		return outputParameter;
	}
}
