package com.erp.dashboard.api;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erp.dashboard.model.api.InfCopReceiptTempAPI;
import com.erp.dashboard.model.api.InputParameter;
import com.erp.dashboard.model.api.OutputParameter;
import com.erp.dashboard.model.api.OutputParameterReceiptTemp;
import com.erp.dashboard.service.IERPService;
import com.erp.dashboard.utils.ERPUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class InfCOPReceiptController {
	@Autowired
	private IERPService erpService; 
	
	ObjectMapper mapper = new ObjectMapper();
	
	private static Logger logger = LogManager.getLogger(InfCOPReceiptController.class);
	
	public boolean validateRequireField(InputParameter inputParameter) {
		if(inputParameter == null) {
			return false;
		}else if(ERPUtils.collectionIsEmpty(inputParameter.getRevenue())) {
			return false;
		}else {
			for(InfCopReceiptTempAPI api : inputParameter.getRevenue()) {
				if(StringUtils.isBlank(api.getAr_receipt_batch())) {
					return false;
				}else if(StringUtils.isBlank(api.getAr_receipt_number())) {
					return false;
				}else if(StringUtils.isBlank(api.getAr_receipt_date())) {
					return false;
				}else if(StringUtils.isBlank(api.getAr_gl_date())) {
					return false;
				}else if(api.getAr_amount_header() == null) {
					return false;
				}else if(StringUtils.isBlank(api.getAr_comments_header())) {
					return false;
				}else if(StringUtils.isBlank(api.getAr_pos_type())) {
					return false;
				}else if(api.getAr_amount_dis() == null) {
					return false;
				}else if(StringUtils.isBlank(api.getAr_comments_line())) {
					return false;
				}else if(StringUtils.isBlank(api.getAr_shop_name())) {
					return false;
				}else if(StringUtils.isBlank(api.getAr_source())) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	@PostMapping("/revenue_data")
	public OutputParameterReceiptTemp postRevenueData(@RequestBody InputParameter inputParameter) {
		OutputParameterReceiptTemp outputParameter = new OutputParameterReceiptTemp();
		try {
			logger.info(mapper.writeValueAsString(inputParameter));
			
			if(!validateRequireField(inputParameter)) {
				outputParameter.setStatus_code("100");
				outputParameter.setStatus_desc("Require Information Parameter");
			}else {
				erpService.insertInfCOPReceiptTemp(inputParameter.getRevenue());
				
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
	
	@GetMapping("/cost_center/{name}")
	public OutputParameter getCostCenter(@PathVariable("name") String name) {
		OutputParameter outputParameter = new OutputParameter();
		try {
			logger.info("InfCOPReceiptController getCostCenter name : " + name);
			
			if(StringUtils.isBlank(name)) {
				outputParameter.setStatus_code("100");
				outputParameter.setStatus_desc("Require Information Parameter");
			}else {
				List<String> costCenters = erpService.queryCostCenterByName(name);
				
				if(!ERPUtils.collectionIsEmpty(costCenters)) {
					if(costCenters.size() == 1) {
						outputParameter.setCost_center(costCenters.get(0));
						outputParameter.setStatus_code("000");
						outputParameter.setStatus_desc("Success Requisition");
					}else {
						outputParameter.setStatus_code("002");
						outputParameter.setStatus_desc("Can get multiple cost center");
					}
				}else {
					outputParameter.setStatus_code("001");
					outputParameter.setStatus_desc("Data not found");
				}
			}
		} catch (Exception e) {
			outputParameter.setStatus_code("999");
			outputParameter.setStatus_desc(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		
		return outputParameter;
	}
}
