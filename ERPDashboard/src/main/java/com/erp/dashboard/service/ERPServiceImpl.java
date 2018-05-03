package com.erp.dashboard.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dashboard.dao.IInfCopReceiptTempDao;
import com.erp.dashboard.dao.IPRCommissionTempDao;
import com.erp.dashboard.dao.IUserDao;
import com.erp.dashboard.entity.Accounting;
import com.erp.dashboard.entity.User;
import com.erp.dashboard.model.InfCopReceiptTemp;
import com.erp.dashboard.model.InfCopReceiptTempChart;
import com.erp.dashboard.model.api.AgentServiceFee;
import com.erp.dashboard.model.api.InfCopReceiptTempAPI;

@Service
public class ERPServiceImpl implements IERPService {
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IInfCopReceiptTempDao copReceiptTempDao;
	@Autowired
	private IPRCommissionTempDao commissionTempDao;
	
	@Override
	public User checkUserLogin(User user){
		return userDao.checkUserLogin(user);
	}
	
	@Override
	public List<InfCopReceiptTempChart> getReceiptTempByType(Date date){
		return copReceiptTempDao.getReceiptTempByType(date);
	}
	
	@Override
	public List<InfCopReceiptTemp> getReceiptTempDetail(String type, Date date){
		return copReceiptTempDao.getReceiptTempDetail(type, date);
	}
	
	@Override
	public void saveReceiptTempDetails(List<InfCopReceiptTemp> copReceiptTemps){
		copReceiptTempDao.saveReceiptTempDetails(copReceiptTemps);
	}
	
	@Override
	public List<InfCopReceiptTempChart> getReceiptTempParcelShop(Date date){
		return copReceiptTempDao.getReceiptTempParcelShop(date);
	}
	
	@Override
	public void insertInfCOPReceiptTemp(List<InfCopReceiptTempAPI> copReceiptTemps) {
		copReceiptTempDao.insertInfCOPReceiptTemp(copReceiptTemps);
	}
	
	@Override
	public List<String> queryCostCenterByName(String name) {
		return copReceiptTempDao.queryCostCenterByName(name);
	}
	
	@Override
	public List<Accounting> queryAccountingByDate(String date) {
		return copReceiptTempDao.queryAccountingByDate(date);
	}
	
	public void insertPRCommissionTempList(List<AgentServiceFee> agentServiceFees) {
		commissionTempDao.insertPRCommissionTempList(agentServiceFees);
	}
}
