package com.erp.dashboard.service;

import java.util.Date;
import java.util.List;

import com.erp.dashboard.model.InfCopReceiptTemp;
import com.erp.dashboard.model.InfCopReceiptTempChart;
import com.erp.dashboard.model.User;


public interface IERPService {
	public List<User> getAllUsers();
	public User checkUserLogin(User user);
	public List<InfCopReceiptTempChart> getReceiptTempByType(Date date);
	public List<InfCopReceiptTemp> getReceiptTempDetail(String type, Date date);
	public void saveReceiptTempDetails(List<InfCopReceiptTemp> copReceiptTemps);
	public List<InfCopReceiptTempChart> getReceiptTempParcelShop(Date date);
}
