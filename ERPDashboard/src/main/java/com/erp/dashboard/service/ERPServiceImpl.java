package com.erp.dashboard.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dashboard.dao.IInfCopReceiptTempDao;
import com.erp.dashboard.dao.IUserDao;
import com.erp.dashboard.model.InfCopReceiptTemp;
import com.erp.dashboard.model.InfCopReceiptTempChart;
import com.erp.dashboard.model.User;

@Service
public class ERPServiceImpl implements IERPService {
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IInfCopReceiptTempDao copReceiptTempDao;
	
	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
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
}
