package com.erp.dashboard.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.dashboard.dao.IInfCopReceiptTempDao;
import com.erp.dashboard.dao.IPRCommissionTempDao;
import com.erp.dashboard.dao.IUserDao;
import com.erp.dashboard.entity.Accounting;
import com.erp.dashboard.entity.User;
import com.erp.dashboard.model.InfCopReceiptTemp;
import com.erp.dashboard.model.api.AgentServiceFee;
import com.erp.dashboard.model.api.InfCopReceiptTempAPI;

@Service
@Transactional
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
	
//	@Override
//	public List<InfCopReceiptTempChart> getReceiptTempByType(Date date){
//		return copReceiptTempDao.getReceiptTempByType(date);
//	}
	
	@Override
	public List<InfCopReceiptTemp> getReceiptTempDetail(String type, Date date){
		return copReceiptTempDao.getReceiptTempDetail(type, date);
	}
	
	public InfCopReceiptTemp genInfCopReceiptTempSave(Date newDate, Date oldDate, String type
								, String status, String shopCode, String glStr, BigDecimal amountDis
								, List<String> notIn) {
		InfCopReceiptTemp copReceiptTemp = new InfCopReceiptTemp();
		copReceiptTemp.setArReceiptDate(newDate);
		copReceiptTemp.setOldArReceiptDate(oldDate);
		copReceiptTemp.setArPosType(type);
		copReceiptTemp.setStatus(status);
		copReceiptTemp.setShopCode(shopCode);
		copReceiptTemp.setArGlDateStr(glStr);
		copReceiptTemp.setArAmountDis(amountDis);
		copReceiptTemp.setNotInArPosType(notIn);
		
		return copReceiptTemp;
	}
	
	@Override
	public void saveReceiptTempDetail(InfCopReceiptTemp copReceiptTemp){
		List<String> notIn = new ArrayList<>();
		notIn.add("ADJ1");
		notIn.add("ADJ2");
		notIn.add("ADJ3");
		notIn.add("ADJ4");
		notIn.add("ADJ5");
		notIn.add("ADJ6");
		notIn.add("ADJ7");
		notIn.add("ADJ8");
		notIn.add("ADJ9");
		
		InfCopReceiptTemp receiptTemp = genInfCopReceiptTempSave(copReceiptTemp.getArReceiptDate(), copReceiptTemp.getOldArReceiptDate()
				, "ADJ1", copReceiptTemp.getStatus(), copReceiptTemp.getShopCode(), copReceiptTemp.getArGlDateStr(), copReceiptTemp.getBonus()
				, null);
		copReceiptTempDao.saveReceiptTempDetail(receiptTemp);
		
		receiptTemp = genInfCopReceiptTempSave(copReceiptTemp.getArReceiptDate(), copReceiptTemp.getOldArReceiptDate()
				, "ADJ2", copReceiptTemp.getStatus(), copReceiptTemp.getShopCode(), copReceiptTemp.getArGlDateStr(), copReceiptTemp.getAdjOther()
				, null);
		copReceiptTempDao.saveReceiptTempDetail(receiptTemp);
		
		receiptTemp = genInfCopReceiptTempSave(copReceiptTemp.getArReceiptDate(), copReceiptTemp.getOldArReceiptDate()
				, "ADJ3", copReceiptTemp.getStatus(), copReceiptTemp.getShopCode(), copReceiptTemp.getArGlDateStr(), copReceiptTemp.getAdjReturnCharge()
				, null);
		copReceiptTempDao.saveReceiptTempDetail(receiptTemp);
		
		receiptTemp = genInfCopReceiptTempSave(copReceiptTemp.getArReceiptDate(), copReceiptTemp.getOldArReceiptDate()
				, "ADJ4", copReceiptTemp.getStatus(), copReceiptTemp.getShopCode(), copReceiptTemp.getArGlDateStr(), copReceiptTemp.getSuspense()
				, null);
		copReceiptTempDao.saveReceiptTempDetail(receiptTemp);
		
		receiptTemp = genInfCopReceiptTempSave(copReceiptTemp.getArReceiptDate(), copReceiptTemp.getOldArReceiptDate()
				, "ADJ5", copReceiptTemp.getStatus(), copReceiptTemp.getShopCode(), copReceiptTemp.getArGlDateStr(), copReceiptTemp.getWithholdingTax()
				, null);
		copReceiptTempDao.saveReceiptTempDetail(receiptTemp);
		
		receiptTemp = genInfCopReceiptTempSave(copReceiptTemp.getArReceiptDate(), copReceiptTemp.getOldArReceiptDate()
				, "ADJ6", copReceiptTemp.getStatus(), copReceiptTemp.getShopCode(), copReceiptTemp.getArGlDateStr(), copReceiptTemp.getPromotion()
				, null);
		copReceiptTempDao.saveReceiptTempDetail(receiptTemp);
		
		receiptTemp = genInfCopReceiptTempSave(copReceiptTemp.getArReceiptDate(), copReceiptTemp.getOldArReceiptDate()
				, "ADJ7", copReceiptTemp.getStatus(), copReceiptTemp.getShopCode(), copReceiptTemp.getArGlDateStr(), copReceiptTemp.getBankCharge()
				, null);
		copReceiptTempDao.saveReceiptTempDetail(receiptTemp);
		
		receiptTemp = genInfCopReceiptTempSave(copReceiptTemp.getArReceiptDate(), copReceiptTemp.getOldArReceiptDate()
				, "ADJ8", copReceiptTemp.getStatus(), copReceiptTemp.getShopCode(), copReceiptTemp.getArGlDateStr(), copReceiptTemp.getCreditCard()
				, null);
		copReceiptTempDao.saveReceiptTempDetail(receiptTemp);
		
		receiptTemp = genInfCopReceiptTempSave(copReceiptTemp.getArReceiptDate(), copReceiptTemp.getOldArReceiptDate()
				, "ADJ9", copReceiptTemp.getStatus(), copReceiptTemp.getShopCode(), copReceiptTemp.getArGlDateStr(), copReceiptTemp.getAdjLinePay()
				, null);
		copReceiptTempDao.saveReceiptTempDetail(receiptTemp);
		
		receiptTemp = genInfCopReceiptTempSave(copReceiptTemp.getArReceiptDate(), copReceiptTemp.getOldArReceiptDate()
				, null, copReceiptTemp.getStatus(), copReceiptTemp.getShopCode(), copReceiptTemp.getArGlDateStr(), copReceiptTemp.getAdjLinePay()
				, notIn);
		copReceiptTempDao.saveReceiptTempDetail(receiptTemp);
	}
	
//	@Override
//	public List<InfCopReceiptTempChart> getReceiptTempParcelShop(Date date){
//		return copReceiptTempDao.getReceiptTempParcelShop(date);
//	}
	
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
