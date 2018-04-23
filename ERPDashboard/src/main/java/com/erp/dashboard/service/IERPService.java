package com.erp.dashboard.service;

import java.util.Date;
import java.util.List;

import com.erp.dashboard.entity.Accounting;
import com.erp.dashboard.entity.User;
import com.erp.dashboard.model.InfCopReceiptTemp;
import com.erp.dashboard.model.InfCopReceiptTempChart;
import com.erp.dashboard.model.api.InfCopReceiptTempAPI;


public interface IERPService {
	public User checkUserLogin(User user);
	public List<InfCopReceiptTempChart> getReceiptTempByType(Date date);
	public List<InfCopReceiptTemp> getReceiptTempDetail(String type, Date date);
	public void saveReceiptTempDetails(List<InfCopReceiptTemp> copReceiptTemps);
	public List<InfCopReceiptTempChart> getReceiptTempParcelShop(Date date);
	public void insertInfCOPReceiptTemp(List<InfCopReceiptTempAPI> copReceiptTemps);
	public List<String> queryCostCenterByName(String name);
	public List<Accounting> queryAccountingByDate(String date);
}
