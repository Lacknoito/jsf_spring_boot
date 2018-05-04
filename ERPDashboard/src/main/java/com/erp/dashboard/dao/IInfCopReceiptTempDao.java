package com.erp.dashboard.dao;

import java.util.Date;
import java.util.List;

import com.erp.dashboard.entity.Accounting;
import com.erp.dashboard.model.InfCopReceiptTemp;
import com.erp.dashboard.model.InfCopReceiptTempChart;
import com.erp.dashboard.model.api.InfCopReceiptTempAPI;

public interface IInfCopReceiptTempDao {
//	public List<InfCopReceiptTempChart> getReceiptTempByType(Date date);
	public List<InfCopReceiptTemp> getReceiptTempDetail(String type, Date date);
	public void saveReceiptTempDetail(InfCopReceiptTemp copReceiptTemps);
//	public List<InfCopReceiptTempChart> getReceiptTempParcelShop(Date date);
	public void insertInfCOPReceiptTemp(List<InfCopReceiptTempAPI> copReceiptTemps);
	public List<String> queryCostCenterByName(String name);
	public List<Accounting> queryAccountingByDate(String date);
}
