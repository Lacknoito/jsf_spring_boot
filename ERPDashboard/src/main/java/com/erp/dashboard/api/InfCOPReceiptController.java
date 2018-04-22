package com.erp.dashboard.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erp.dashboard.model.api.InputParameter;
import com.erp.dashboard.model.api.OutputParameter;
import com.erp.dashboard.service.IERPService;

@RestController
public class InfCOPReceiptController {
	@Autowired
	private IERPService erpService; 
	
	@PostMapping("/revenue_data")
	public OutputParameter postRevenueData(@RequestBody InputParameter inputParameter) {
		OutputParameter outputParameter = new OutputParameter();
		try {
			erpService.insertInfCOPReceiptTemp(inputParameter.getRevenue());
			
			outputParameter.setStatus_code("000");
			outputParameter.setStatus_desc("Success Requisition");
		} catch (Exception e) {
			
		}
		
		return outputParameter;
	}
	
	@GetMapping("/cost_center/{id}")
	public OutputParameter getCostCenter(@PathVariable("id") int id) {
		System.out.println("id : " + id);
		
		OutputParameter outputParameter = new OutputParameter();
		try {
			
			outputParameter.setStatus_code("000");
			outputParameter.setStatus_desc("Success Requisition");
		} catch (Exception e) {
			
		}
		
		return outputParameter;
	}
}
