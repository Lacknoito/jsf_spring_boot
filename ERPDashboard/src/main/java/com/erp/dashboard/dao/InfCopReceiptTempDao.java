package com.erp.dashboard.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Query;
import com.erp.dashboard.model.InfCopReceiptTemp;
import com.erp.dashboard.model.InfCopReceiptTempChart;
import com.erp.dashboard.utils.ERPUtils;

@Transactional
@Repository
public class InfCopReceiptTempDao implements IInfCopReceiptTempDao{
	@PersistenceContext	
	private EntityManager entityManager;	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<InfCopReceiptTempChart> getReceiptTempByType(Date date) {
		StringBuilder str = new StringBuilder();
		str.append(" select receiptTemp.Branchtype as branchType, count(1) as count ");
		str.append(" , sum(case when receiptTemp.record_status is not null and receiptTemp.record_status = 'N' then 0 ");
		str.append(" 		else receiptTemp.ar_amount_header end) as amountHeader ");
		str.append(" from ( ");
		str.append(" 	select case when r.ar_shop_name = '181002' then :bsd ");
		str.append(" 		when r.ar_shop_name like '19%' then :dc ");
		str.append(" 		when r.ar_shop_name like '182%' then :upc ");
		str.append(" 		else :fc_ke end as branchType, r.ar_amount_header ");
		str.append(" 		, r.record_status ");
		str.append(" 	from XXINF_COP_RECEIPT_TEMP r ");
		str.append(" 	inner join FND_FLEX_VALUES b on b.FLEX_VALUE = r.ar_shop_name ");
		str.append(" 		and b.FLEX_VALUE_SET_ID = :valueSetId ");
		str.append(" 	inner join FND_FLEX_VALUES_TL t on b.FLEX_VALUE_ID = t.FLEX_VALUE_ID ");
		str.append(" 	where trunc(r.ar_receipt_date) = to_date(:date,'MM/DD/YYYY') ");
		str.append(" 	group by r.ar_shop_name, r.ar_receipt_date, r.ar_amount_header, r.record_status ");
		str.append(" ) receiptTemp ");
		str.append(" group by receiptTemp.Branchtype ");

		Query query =  entityManager.unwrap(org.hibernate.Session.class)
					.createSQLQuery(str.toString())
					.addScalar("branchType")
					.addScalar("count")
					.addScalar("amountHeader")
					.setResultTransformer(Transformers.aliasToBean(InfCopReceiptTempChart.class));

		query.setParameter("valueSetId", ERPUtils.FIX_FLEX_VALUE_SET_ID);
		query.setParameter("dc", ERPUtils.DISTRIBUTION_CENTER);
		query.setParameter("bsd", ERPUtils.BANGKOK_SAME_DAY);
		query.setParameter("upc", ERPUtils.REGIONAL_PARCEL_SHOP);
		query.setParameter("fc_ke", ERPUtils.FRANCHISE_KERRYEXPRESS_SHOP);
		query.setParameter("date", ERPUtils.convertDateToStringFormat(date, "MM/dd/YYYY"));
		
		List<InfCopReceiptTempChart> copReceiptTemps =  query.list();
		return copReceiptTemps;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<InfCopReceiptTempChart> getReceiptTempParcelShop(Date date) {
		StringBuilder str = new StringBuilder();
		str.append(" select receiptTemp.Branchtype as branchType, count(1) as count ");
		str.append(" 	, sum(case when receiptTemp.record_status is not null and receiptTemp.record_status = 'N' then 0 ");
		str.append(" 			else receiptTemp.ar_amount_header end) as amountHeader ");
		str.append(" from ( ");
		str.append(" 	select ");
		str.append(" 		case when r.ar_shop_name like '101%' then :bkk ");
		str.append(" 			when r.ar_shop_name like '102%' then :greater ");
		str.append(" 			when r.ar_shop_name like '103%' then :central ");
		str.append(" 			when r.ar_shop_name like '104%' then :east ");
		str.append(" 			when r.ar_shop_name like '105%' then :north ");
		str.append(" 			when r.ar_shop_name like '106%' then :northeast ");
		str.append(" 			when r.ar_shop_name like '107%' then :south ");
		str.append(" 			when r.ar_shop_name like '108%' then :samzone ");
		str.append(" 			when r.ar_shop_name like '109%' then :dcsp ");
		str.append(" 			when r.ar_shop_name like '181%' then :bsd ");
		str.append(" 			else r.ar_shop_name end as branchType ");
		str.append(" 		, r.ar_amount_header, r.record_status  ");
		str.append(" 	from XXINF_COP_RECEIPT_TEMP r  ");
		str.append(" 	inner join FND_FLEX_VALUES b on b.FLEX_VALUE = r.ar_shop_name  ");
		str.append(" 		and b.FLEX_VALUE_SET_ID = :valueSetId ");
		str.append(" 	inner join FND_FLEX_VALUES_TL t on b.FLEX_VALUE_ID = t.FLEX_VALUE_ID  ");
		str.append(" 	where trunc(r.ar_receipt_date) = to_date(:date,'MM/DD/YYYY') ");
		str.append(" 		and r.ar_shop_name <> '181002' ");
		str.append(" 		and r.ar_shop_name not like '182%' ");
		str.append(" 		and r.ar_shop_name not like '19%' ");
		str.append(" 	group by r.ar_shop_name, r.ar_receipt_date, r.ar_amount_header, r.record_status ");
		str.append(" ) receiptTemp  ");
		str.append(" group by receiptTemp.Branchtype ");

		Query query =  entityManager.unwrap(org.hibernate.Session.class)
					.createSQLQuery(str.toString())
					.addScalar("branchType")
					.addScalar("count")
					.addScalar("amountHeader")
					.setResultTransformer(Transformers.aliasToBean(InfCopReceiptTempChart.class));

		query.setParameter("bkk", ERPUtils.REGION.T_BKK);
		query.setParameter("greater", ERPUtils.REGION.T_GREATER);
		query.setParameter("central", ERPUtils.REGION.T_CENTRAL);
		query.setParameter("east", ERPUtils.REGION.T_EAST);
		query.setParameter("north", ERPUtils.REGION.T_NORTH);
		query.setParameter("northeast", ERPUtils.REGION.T_NORTHEAST);
		query.setParameter("south", ERPUtils.REGION.T_SOUTH);
		query.setParameter("samzone", ERPUtils.REGION.T_SAMZONE);
		query.setParameter("dcsp", ERPUtils.REGION.T_DCSP);
		query.setParameter("bsd", ERPUtils.REGION.T_BSD);
		query.setParameter("valueSetId", ERPUtils.FIX_FLEX_VALUE_SET_ID);
		query.setParameter("date", ERPUtils.convertDateToStringFormat(date, "MM/dd/YYYY"));
		
		List<InfCopReceiptTempChart> copReceiptTemps =  query.list();
		return copReceiptTemps;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<InfCopReceiptTemp> getReceiptTempDetail(String type, Date date){
		Map<String, Object> param = new HashMap<String, Object>();
		
		StringBuilder str = new StringBuilder();
		str.append(" select ");
		str.append(" 	r.ar_shop_name as shopCode ");
		str.append(" 	, r.ar_amount_header as amountHeader ");
		str.append(" 	, t.description as shopName ");
		str.append(" 	, r.ar_receipt_date as arReceiptDate ");
		str.append(" 	, r.ar_receipt_date as oldArReceiptDate ");
		str.append(" 	, r.record_status as status ");
		str.append(" 	, r.record_status as oldStatus ");
		str.append(" 	, 0 as cash ");
		str.append(" 	, sum(case when r.ar_pos_type = 'RABBIT' then r.ar_amount_dis else 0 end) as rabit  ");
		str.append(" 	, sum(case when r.ar_pos_type = 'CREDIT BBL' then r.ar_amount_dis else 0 end) as creditBBL ");
		str.append(" 	, sum(case when r.ar_pos_type = 'CREDIT SCB' then r.ar_amount_dis else 0 end) as creditSCB ");
		str.append(" 	, sum(case when r.ar_pos_type = 'LINEPAY' then r.ar_amount_dis else 0 end) as linePay ");
		str.append(" 	, sum(case when r.ar_pos_type = 'ADJ1' then r.ar_amount_dis else 0 end) as bonus ");
		str.append(" 	, sum(case when r.ar_pos_type = 'ADJ2' then r.ar_amount_dis else 0 end) as adjOther ");
		str.append(" 	, sum(case when r.ar_pos_type = 'ADJ3' then r.ar_amount_dis else 0 end) as adjReturnCharge ");
		str.append(" 	, sum(case when r.ar_pos_type = 'ADJ4' then r.ar_amount_dis else 0 end) as suspense ");
		str.append(" 	, sum(case when r.ar_pos_type = 'ADJ5' then r.ar_amount_dis else 0 end) as withholdingTax ");
		str.append(" 	, sum(case when r.ar_pos_type = 'ADJ6' then r.ar_amount_dis else 0 end) as promotion ");
		str.append(" 	, sum(case when r.ar_pos_type = 'INSUR' then r.ar_amount_dis else 0 end) as insurance ");
		str.append(" from XXINF_COP_RECEIPT_TEMP r ");
		str.append(" inner join FND_FLEX_VALUES b on b.FLEX_VALUE = r.ar_shop_name  ");
		str.append(" 	and b.FLEX_VALUE_SET_ID = :valueSetId ");
		str.append(" inner join FND_FLEX_VALUES_TL t on b.FLEX_VALUE_ID = t.FLEX_VALUE_ID  ");
		str.append(" where trunc(r.ar_receipt_date) = to_date(:date,'MM/DD/YYYY')  ");
		
		param.put("valueSetId", ERPUtils.FIX_FLEX_VALUE_SET_ID);
		param.put("date", ERPUtils.convertDateToStringFormat(date, "MM/dd/YYYY"));

		if(ERPUtils.BANGKOK_SAME_DAY.equalsIgnoreCase(type)) {
			str.append(" and r.ar_shop_name = :BSDCodr ");
			param.put("BSDCodr", ERPUtils.BANGKOK_SAME_DAY_CODE);
		}else if(ERPUtils.DISTRIBUTION_CENTER.equalsIgnoreCase(type)) {
			str.append(" and r.ar_shop_name like '19%' ");
		}else if(ERPUtils.REGIONAL_PARCEL_SHOP.equalsIgnoreCase(type)) {
			str.append(" and r.ar_shop_name like '182%' ");
		}else {
			if(ERPUtils.REGION.T_BKK.equalsIgnoreCase(type)) {
				str.append(" and r.ar_shop_name like :shop ");
				param.put("shop", ERPUtils.REGION.V_BKK + "%");
			}else if(ERPUtils.REGION.T_GREATER.equalsIgnoreCase(type)) {
				str.append(" and r.ar_shop_name like :shop ");
				param.put("shop", ERPUtils.REGION.V_GREATER + "%");
			}else if(ERPUtils.REGION.T_CENTRAL.equalsIgnoreCase(type)) {
				str.append(" and r.ar_shop_name like :shop ");
				param.put("shop", ERPUtils.REGION.V_CENTRAL + "%");
			}else if(ERPUtils.REGION.T_EAST.equalsIgnoreCase(type)) {
				str.append(" and r.ar_shop_name like :shop ");
				param.put("shop", ERPUtils.REGION.V_EAST + "%");
			}else if(ERPUtils.REGION.T_NORTH.equalsIgnoreCase(type)) {
				str.append(" and r.ar_shop_name like :shop ");
				param.put("shop", ERPUtils.REGION.V_NORTH + "%");
			}else if(ERPUtils.REGION.T_NORTHEAST.equalsIgnoreCase(type)) {
				str.append(" and r.ar_shop_name like :shop ");
				param.put("shop", ERPUtils.REGION.V_NORTHEAST + "%");
			}else if(ERPUtils.REGION.T_SOUTH.equalsIgnoreCase(type)) {
				str.append(" and r.ar_shop_name like :shop ");
				param.put("shop", ERPUtils.REGION.V_SOUTH + "%");
			}else if(ERPUtils.REGION.T_SAMZONE.equalsIgnoreCase(type)) {
				str.append(" and r.ar_shop_name like :shop ");
				param.put("shop", ERPUtils.REGION.V_SAMZONE + "%");
			}else if(ERPUtils.REGION.T_DCSP.equalsIgnoreCase(type)) {
				str.append(" and r.ar_shop_name like :shop ");
				param.put("shop", ERPUtils.REGION.V_DCSP + "%");
			}else if(ERPUtils.REGION.T_BSD.equalsIgnoreCase(type)) {
				str.append(" and r.ar_shop_name like :shop ");
				param.put("shop", ERPUtils.REGION.V_BSD + "%");
			}
		}
		
		str.append(" group by r.ar_shop_name, t.description, r.ar_receipt_date, r.ar_amount_header, r.record_status ");
		str.append(" order by r.ar_shop_name, r.ar_receipt_date, r.record_status ");
		
		Query query =  entityManager.unwrap(org.hibernate.Session.class)
				.createSQLQuery(str.toString())
				.addScalar("shopCode")
				.addScalar("amountHeader")
				.addScalar("shopName")
				.addScalar("arReceiptDate")
				.addScalar("oldArReceiptDate")
				.addScalar("cash")
				.addScalar("rabit")
				.addScalar("creditBBL")
				.addScalar("creditSCB")
				.addScalar("linePay")
				.addScalar("bonus")
				.addScalar("adjOther")
				.addScalar("adjReturnCharge")
				.addScalar("suspense")
				.addScalar("withholdingTax")
				.addScalar("promotion")
				.addScalar("insurance")
				.addScalar("status")
				.addScalar("oldStatus")
				.setResultTransformer(Transformers.aliasToBean(InfCopReceiptTemp.class));
		
		ERPUtils.setParameterByMap(param, query);
		
		List<InfCopReceiptTemp> copReceiptTemps =  query.list();
		
		return copReceiptTemps;
	}
	
	public void saveReceiptTempDetails(List<InfCopReceiptTemp> copReceiptTemps) {
		for(InfCopReceiptTemp copReceiptTemp : copReceiptTemps) {
			Map<String, Object> param = new HashMap<String, Object>();
			
			StringBuilder str = new StringBuilder();
			str.append(" update XXINF_COP_RECEIPT_TEMP r ");
			str.append(" set r.ar_receipt_date = to_date(:newDate,'MM/DD/YYYY') ");
			
			if("N".equalsIgnoreCase(copReceiptTemp.getStatus())) {
				str.append(" , r.record_status = :status ");
				param.put("status", copReceiptTemp.getStatus());
			}
			
			str.append(" where r.ar_shop_name = :shopCode ");
			str.append(" 	and r.ar_receipt_date = to_date(:oldDate,'MM/DD/YYYY')  ");
			str.append(" 	and r.ar_amount_header = :arAmountHeader ");
			
			param.put("shopCode", copReceiptTemp.getShopCode());
			param.put("newDate", ERPUtils.convertDateToStringFormat(copReceiptTemp.getArReceiptDate(), "MM/dd/YYYY"));
			param.put("oldDate", ERPUtils.convertDateToStringFormat(copReceiptTemp.getOldArReceiptDate(), "MM/dd/YYYY"));
			param.put("arAmountHeader", copReceiptTemp.getAmountHeader());
			
			Query query =  entityManager.unwrap(org.hibernate.Session.class).createSQLQuery(str.toString());
			
			ERPUtils.setParameterByMap(param, query);
			
			query.executeUpdate();
		}
	}
}
