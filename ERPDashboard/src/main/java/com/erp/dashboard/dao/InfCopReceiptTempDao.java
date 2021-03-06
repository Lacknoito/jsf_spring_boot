package com.erp.dashboard.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.erp.dashboard.entity.Accounting;
import com.erp.dashboard.model.InfCopReceiptTemp;
import com.erp.dashboard.model.InfCopReceiptTempChart;
import com.erp.dashboard.model.api.InfCopReceiptTempAPI;
import com.erp.dashboard.utils.ERPUtils;

@Repository
public class InfCopReceiptTempDao implements IInfCopReceiptTempDao{
	private static Logger logger = LogManager.getLogger(InfCopReceiptTempDao.class);
	
	@PersistenceContext	
	private EntityManager entityManager;	
	
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<InfCopReceiptTempChart> getReceiptTempByType(Date date) {
//		StringBuilder str = new StringBuilder();
//		str.append(" select receiptTemp.Branchtype as branchType, count(1) as count ");
//		str.append(" , sum(case when receiptTemp.record_status is not null and receiptTemp.record_status = 'N' then 0 ");
//		str.append(" 		else receiptTemp.ar_amount_header end) as amountHeader ");
//		str.append(" from ( ");
//		str.append(" 	select case when r.ar_shop_name = '181002' then :bsd ");
//		str.append(" 		when r.ar_shop_name like '19%' then :fc_ke ");
//		str.append(" 		when r.ar_shop_name like '182%' then :upc ");
//		str.append(" 		else :dc end as branchType, sum(r.ar_amount_header) as ar_amount_header ");
//		str.append(" 		, r.record_status ");
//		str.append(" 	from XXINF_COP_RECEIPT_TEMP r ");
//		str.append(" 	inner join FND_FLEX_VALUES b on b.FLEX_VALUE = r.ar_shop_name ");
//		str.append(" 		and b.FLEX_VALUE_SET_ID = :valueSetId ");
//		str.append(" 	inner join FND_FLEX_VALUES_TL t on b.FLEX_VALUE_ID = t.FLEX_VALUE_ID ");
//		str.append(" 	where r.ar_receipt_date >= to_date(:date,'MM/DD/YYYY')  ");
//		str.append(" 		and r.ar_receipt_date < to_date(:date,'MM/DD/YYYY') + 1  ");
//		str.append(" 		and r.ar_amount_dis <> 0 ");
//		str.append(" 	group by r.ar_shop_name, r.ar_receipt_date, r.ar_gl_date, r.record_status ");
//		str.append(" ) receiptTemp ");
//		str.append(" group by receiptTemp.Branchtype ");
//
//		Query query =  entityManager.unwrap(Session.class)
//					.createSQLQuery(str.toString())
//					.addScalar("branchType")
//					.addScalar("count")
//					.addScalar("amountHeader")
//					.setResultTransformer(Transformers.aliasToBean(InfCopReceiptTempChart.class));
//
//		query.setParameter("valueSetId", ERPUtils.FIX_FLEX_VALUE_SET_ID);
//		query.setParameter("dc", ERPUtils.DISTRIBUTION_CENTER);
//		query.setParameter("bsd", ERPUtils.BANGKOK_SAME_DAY);
//		query.setParameter("upc", ERPUtils.REGIONAL_PARCEL_SHOP);
//		query.setParameter("fc_ke", ERPUtils.FRANCHISE_KERRYEXPRESS_SHOP);
//		query.setParameter("date", ERPUtils.convertDateToStringFormat(date, "MM/dd/YYYY"));
//		
//		List<InfCopReceiptTempChart> copReceiptTemps =  query.list();
//		return copReceiptTemps;
//	}
	
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<InfCopReceiptTempChart> getReceiptTempParcelShop(Date date) {
//		StringBuilder str = new StringBuilder();
//		str.append(" select receiptTemp.Branchtype as branchType, count(1) as count ");
//		str.append(" 	, sum(case when receiptTemp.record_status is not null and receiptTemp.record_status = 'N' then 0 ");
//		str.append(" 			else receiptTemp.ar_amount_header end) as amountHeader ");
//		str.append(" from ( ");
//		str.append(" 	select ");
//		str.append(" 		case when r.ar_shop_name like '101%' then :bkk ");
//		str.append(" 			when r.ar_shop_name like '102%' then :greater ");
//		str.append(" 			when r.ar_shop_name like '103%' then :central ");
//		str.append(" 			when r.ar_shop_name like '104%' then :east ");
//		str.append(" 			when r.ar_shop_name like '105%' then :north ");
//		str.append(" 			when r.ar_shop_name like '106%' then :northeast ");
//		str.append(" 			when r.ar_shop_name like '107%' then :south ");
//		str.append(" 			when r.ar_shop_name like '108%' then :samzone ");
//		str.append(" 			when r.ar_shop_name like '109%' then :dcsp ");
//		str.append(" 			when r.ar_shop_name like '181%' then :bsd ");
//		str.append(" 			else r.ar_shop_name end as branchType ");
//		str.append(" 		, sum(r.ar_amount_header) as ar_amount_header, r.record_status  ");
//		str.append(" 	from XXINF_COP_RECEIPT_TEMP r  ");
//		str.append(" 	inner join FND_FLEX_VALUES b on b.FLEX_VALUE = r.ar_shop_name  ");
//		str.append(" 		and b.FLEX_VALUE_SET_ID = :valueSetId ");
//		str.append(" 	inner join FND_FLEX_VALUES_TL t on b.FLEX_VALUE_ID = t.FLEX_VALUE_ID  ");
//		str.append(" 	where r.ar_receipt_date >= to_date(:date,'MM/DD/YYYY') ");
//		str.append(" 		and r.ar_receipt_date < to_date(:date,'MM/DD/YYYY') + 1 ");
//		str.append(" 		and r.ar_shop_name <> '181002' ");
//		str.append(" 		and r.ar_shop_name not like '182%' ");
//		str.append(" 		and r.ar_shop_name not like '19%' ");
//		str.append(" 		and r.ar_amount_dis <> 0 ");
//		str.append(" 	group by r.ar_shop_name, r.ar_receipt_date, r.record_status ");
//		str.append(" ) receiptTemp  ");
//		str.append(" group by receiptTemp.Branchtype ");
//
//		Query query =  entityManager.unwrap(Session.class)
//					.createSQLQuery(str.toString())
//					.addScalar("branchType")
//					.addScalar("count")
//					.addScalar("amountHeader")
//					.setResultTransformer(Transformers.aliasToBean(InfCopReceiptTempChart.class));
//
//		query.setParameter("bkk", ERPUtils.REGION.T_BKK);
//		query.setParameter("greater", ERPUtils.REGION.T_GREATER);
//		query.setParameter("central", ERPUtils.REGION.T_CENTRAL);
//		query.setParameter("east", ERPUtils.REGION.T_EAST);
//		query.setParameter("north", ERPUtils.REGION.T_NORTH);
//		query.setParameter("northeast", ERPUtils.REGION.T_NORTHEAST);
//		query.setParameter("south", ERPUtils.REGION.T_SOUTH);
//		query.setParameter("samzone", ERPUtils.REGION.T_SAMZONE);
//		query.setParameter("dcsp", ERPUtils.REGION.T_DCSP);
//		query.setParameter("bsd", ERPUtils.REGION.T_BSD);
//		query.setParameter("valueSetId", ERPUtils.FIX_FLEX_VALUE_SET_ID);
//		query.setParameter("date", ERPUtils.convertDateToStringFormat(date, "MM/dd/YYYY"));
//		
//		List<InfCopReceiptTempChart> copReceiptTemps =  query.list();
//		return copReceiptTemps;
//	}
	
	public List<InfCopReceiptTemp> getReceiptTempDetail(String type, Date date){
		
		String shop = "";
		if(ERPUtils.REGION.T_BKK.equalsIgnoreCase(type)) {
			shop = ERPUtils.REGION.V_BKK;
		}else if(ERPUtils.REGION.T_GREATER.equalsIgnoreCase(type)) {
			shop = ERPUtils.REGION.V_GREATER;
		}else if(ERPUtils.REGION.T_CENTRAL.equalsIgnoreCase(type)) {
			shop = ERPUtils.REGION.V_CENTRAL;
		}else if(ERPUtils.REGION.T_EAST.equalsIgnoreCase(type)) {
			shop = ERPUtils.REGION.V_EAST;
		}else if(ERPUtils.REGION.T_NORTH.equalsIgnoreCase(type)) {
			shop = ERPUtils.REGION.V_NORTH;
		}else if(ERPUtils.REGION.T_NORTHEAST.equalsIgnoreCase(type)) {
			shop = ERPUtils.REGION.V_NORTHEAST;
		}else if(ERPUtils.REGION.T_SOUTH.equalsIgnoreCase(type)) {
			shop = ERPUtils.REGION.V_SOUTH;
		}else if(ERPUtils.REGION.T_SAMZONE.equalsIgnoreCase(type)) {
			shop = ERPUtils.REGION.V_SAMZONE;
		}else if(ERPUtils.REGION.T_DCSP.equalsIgnoreCase(type)) {
			shop = ERPUtils.REGION.V_DCSP;
		}else if(ERPUtils.REGION.T_BSD.equalsIgnoreCase(type)) {
			shop = ERPUtils.REGION.V_BSD;
		}
		
		shop += "%";
		
		logger.info("date : " + ERPUtils.convertDateToStringFormat(date, "MM/dd/YYYY"));
		logger.info("type : " + type);
		logger.info("shop : " + shop);
		
		List<InfCopReceiptTemp> infCopReceiptTemps = new ArrayList<>();
		StoredProcedureQuery query = entityManager
			    .createStoredProcedureQuery("XX_GET_COP_RECEIPT_TEMP")
			    .registerStoredProcedureParameter(1, String.class, 
			        ParameterMode.IN)
			    .registerStoredProcedureParameter(2, String.class, 
				        ParameterMode.IN)
			    .registerStoredProcedureParameter(3, String.class, 
				        ParameterMode.IN)
			    .registerStoredProcedureParameter(4, Class.class, 
			        ParameterMode.REF_CURSOR)
			    .setParameter(1, ERPUtils.convertDateToStringFormat(date, "MM/dd/YYYY"))
			    .setParameter(2, type)
			    .setParameter(3, shop);
			 
		query.execute();
			
		List<Object[]> resultList = query.getResultList();
		
		for(Object[] result : resultList) {
			InfCopReceiptTemp copReceiptTemp = new InfCopReceiptTemp();
			copReceiptTemp.setShopCode((String) result[0]);
			copReceiptTemp.setAmountHeader((BigDecimal) result[1]);
			copReceiptTemp.setShopName((String) result[2]);
			copReceiptTemp.setArReceiptDate((Date) result[3]);
			copReceiptTemp.setArReceiptDateStr((String) result[4]);
			copReceiptTemp.setArGlDateStr((String) result[5]);
			copReceiptTemp.setStatus((String) result[6]);
			copReceiptTemp.setOldStatus((String) result[7]);
			copReceiptTemp.setCash((BigDecimal) result[8]);
			copReceiptTemp.setFreight((BigDecimal) result[9]);
			copReceiptTemp.setCod((BigDecimal) result[10]);
			copReceiptTemp.setPackageSalePackage((BigDecimal) result[11]);
			copReceiptTemp.setLinePay((BigDecimal) result[12]);
			copReceiptTemp.setCreditBBL((BigDecimal) result[13]);
			copReceiptTemp.setCreditSCB((BigDecimal) result[14]);
			copReceiptTemp.setRabit((BigDecimal) result[15]);
			copReceiptTemp.setTopup((BigDecimal) result[16]);
			copReceiptTemp.setDiscount((BigDecimal) result[17]);
			copReceiptTemp.setInsurance((BigDecimal) result[18]);
			copReceiptTemp.setBonus((BigDecimal) result[19]);
			copReceiptTemp.setAdjOther((BigDecimal) result[20]);
			copReceiptTemp.setAdjReturnCharge((BigDecimal) result[21]);
			copReceiptTemp.setSuspense((BigDecimal) result[22]);
			copReceiptTemp.setWithholdingTax((BigDecimal) result[23]);
			copReceiptTemp.setPromotion((BigDecimal) result[24]);
			copReceiptTemp.setBankCharge((BigDecimal) result[25]);
			copReceiptTemp.setCreditCard((BigDecimal) result[26]);
			copReceiptTemp.setAdjLinePay((BigDecimal) result[27]);
			copReceiptTemp.setVat((BigDecimal) result[28]);
			copReceiptTemp.setOldArReceiptDate((Date) result[29]);
			infCopReceiptTemps.add(copReceiptTemp);
		}
		
		return infCopReceiptTemps;
	}
	
	public void saveReceiptTempDetail(InfCopReceiptTemp copReceiptTemp) {
			Map<String, Object> param = new HashMap<String, Object>();
			
			StringBuilder str = new StringBuilder();
			str.append(" update XXINF_COP_RECEIPT_TEMP r ");
			str.append(" set r.ar_receipt_date = to_date(:newDate,'DD/MM/YYYY') ");
			
			if(StringUtils.isNotBlank(copReceiptTemp.getArPosType())) {
				str.append(" , r.AR_AMOUNT_DIS = :AR_AMOUNT_DIS ");
				param.put("AR_AMOUNT_DIS", copReceiptTemp.getArAmountDis());
			}
			
			if("N".equalsIgnoreCase(copReceiptTemp.getStatus())) {
				str.append(" , r.record_status = :status ");
				param.put("status", copReceiptTemp.getStatus());
			}
			
			str.append(" where r.ar_shop_name = :shopCode ");
			str.append(" 	and r.ar_receipt_date = to_date(:oldDate,'DD/MM/YYYY')  ");
			str.append(" 	and r.ar_gl_date = to_date(:gl_date,'DD/MM/YYYY')  ");
			
			if(StringUtils.isNotBlank(copReceiptTemp.getArPosType())) {
				str.append(" 	and r.AR_POS_TYPE = :AR_POS_TYPE  ");
				param.put("AR_POS_TYPE", copReceiptTemp.getArPosType());
			}else {
				str.append(" 	and r.AR_POS_TYPE not in (:AR_POS_TYPE_List)  ");
				param.put("AR_POS_TYPE_List", copReceiptTemp.getNotInArPosType());
			}
			
			param.put("shopCode", copReceiptTemp.getShopCode());
			param.put("newDate", ERPUtils.convertDateToStringFormat(copReceiptTemp.getArReceiptDate(), ERPUtils.SIMPLE_DATE_FORMAT));
			param.put("oldDate", ERPUtils.convertDateToStringFormat(copReceiptTemp.getOldArReceiptDate(), ERPUtils.SIMPLE_DATE_FORMAT));
			param.put("gl_date", copReceiptTemp.getArGlDateStr());
			
			Query query =  entityManager.unwrap(Session.class).createSQLQuery(str.toString());
			
			ERPUtils.setParameterByMap(param, query);
			
			query.executeUpdate();
	}
	
	public void insertInfCOPReceiptTemp(List<InfCopReceiptTempAPI> copReceiptTemps) {
		StringBuilder str = new StringBuilder();
		str.append(" insert into XXINF_COP_RECEIPT_TEMP( ");
		str.append(" 	ar_receipt_batch ");
		str.append(" 	,ar_receipt_number ");
		str.append(" 	,ar_receipt_date ");
		str.append(" 	,ar_gl_date ");
		str.append(" 	,ar_amount_header ");
		str.append(" 	,ar_comments_header ");
		str.append(" 	,ar_pos_type ");
		str.append(" 	,ar_amount_dis ");
		str.append(" 	,ar_comments_line ");
		str.append(" 	,ar_shop_name ");
		str.append(" 	,ar_source ");
		str.append(" )values( ");
		str.append(" 	:ar_receipt_batch ");
		str.append(" 	, :ar_receipt_number ");
		str.append(" 	, to_date(:ar_receipt_date, 'DD-MON-YY') ");
		str.append(" 	, to_date(:ar_gl_date, 'DD-MON-YY') ");
		str.append(" 	, :ar_amount_header ");
		str.append(" 	, :ar_comments_header ");
		str.append(" 	, :ar_pos_type ");
		str.append(" 	, :ar_amount_dis ");
		str.append(" 	, :ar_comments_line ");
		str.append(" 	, :ar_shop_name ");
		str.append(" 	, :ar_source ");
		str.append(" ) ");
		
		for(InfCopReceiptTempAPI copReceiptTemp : copReceiptTemps) {
			entityManager.createNativeQuery(str.toString())
	                .setParameter("ar_receipt_batch", copReceiptTemp.getAr_receipt_batch())
	                .setParameter("ar_receipt_number", copReceiptTemp.getAr_receipt_number())
	                .setParameter("ar_receipt_date", copReceiptTemp.getAr_receipt_date())
	                .setParameter("ar_gl_date", copReceiptTemp.getAr_gl_date())
	                .setParameter("ar_amount_header", copReceiptTemp.getAr_amount_header())
	                .setParameter("ar_comments_header", copReceiptTemp.getAr_comments_header())
	                .setParameter("ar_pos_type", copReceiptTemp.getAr_pos_type())
	                .setParameter("ar_amount_dis", copReceiptTemp.getAr_amount_dis())
	                .setParameter("ar_comments_line", copReceiptTemp.getAr_comments_line())
	                .setParameter("ar_shop_name", copReceiptTemp.getAr_shop_name())
	                .setParameter("ar_source", copReceiptTemp.getAr_source())
	                .executeUpdate();
		}
	}
	
	public List<String> queryCostCenterByName(String name) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		StringBuilder str = new StringBuilder();
		str.append(" SELECT distinct ffv.flex_value as cost_center ");
		str.append(" FROM fnd_flex_value_sets ffvs , fnd_flex_values ffv , fnd_flex_values_tl ffvt ");
		str.append(" WHERE ffvs.flex_value_set_id = ffv.flex_value_set_id ");
		str.append(" 	and ffv.flex_value_id      = ffvt.flex_value_id ");
		str.append(" 	and ffvs.flex_value_set_name = :typeCostCenter ");
		str.append(" 	and ffv.enabled_flag = :flag ");
		str.append(" 	and ffvt.description is not null ");
		str.append(" 	and upper(:name) LIKE upper(CONCAT('%', ffvt.description)) ");
		
		param.put("typeCostCenter", ERPUtils.TYPE_COST_CENTER);
		param.put("flag", "Y");
		param.put("name", name);
		
		Query query =  entityManager.unwrap(Session.class).createSQLQuery(str.toString());
		
		ERPUtils.setParameterByMap(param, query);
		
		@SuppressWarnings("unchecked")
		List<String> cost_centers = query.list();
		
		return cost_centers;
	}
	
	public List<Accounting> queryAccountingByDate(String date){
		List<Accounting> accountings = new ArrayList<>();
		StoredProcedureQuery query = entityManager
			    .createStoredProcedureQuery("XX_GET_ACCOUNTING")
			    .registerStoredProcedureParameter(1, String.class, 
			        ParameterMode.IN)
			    .registerStoredProcedureParameter(2, Class.class, 
			        ParameterMode.REF_CURSOR)
			    .setParameter(1, date);
			 
		query.execute();
			
		List<Object[]> postComments = query.getResultList();
		
		for(Object[] postComment : postComments) {
			Accounting accounting = new Accounting();
			accounting.setRECEIPT_NUMBER((String) postComment[0]);
			accounting.setACCOUNT_CODE((String) postComment[1]);
			accounting.setACCOUNT_NAME((String) postComment[2]);
			accounting.setACCOUNTED_DR((BigDecimal) postComment[3]);
			accounting.setACCOUNTED_CR((BigDecimal) postComment[4]);
			accounting.setCOST_CENTER((String) postComment[5]);
			accountings.add(accounting);
		}
		
		return accountings;
	}
}
