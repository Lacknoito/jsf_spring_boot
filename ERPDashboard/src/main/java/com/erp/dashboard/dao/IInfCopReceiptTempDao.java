package com.erp.dashboard.dao;

import java.util.Date;
import java.util.List;

import com.erp.dashboard.model.InfCopReceiptTemp;
import com.erp.dashboard.model.InfCopReceiptTempChart;
import com.erp.dashboard.model.api.InfCopReceiptTempAPI;

public interface IInfCopReceiptTempDao {
	public List<InfCopReceiptTempChart> getReceiptTempByType(Date date);
	public List<InfCopReceiptTemp> getReceiptTempDetail(String type, Date date);
	public void saveReceiptTempDetails(List<InfCopReceiptTemp> copReceiptTemps);
	public List<InfCopReceiptTempChart> getReceiptTempParcelShop(Date date);
	public void insertInfCOPReceiptTemp(List<InfCopReceiptTempAPI> copReceiptTemps);
}
