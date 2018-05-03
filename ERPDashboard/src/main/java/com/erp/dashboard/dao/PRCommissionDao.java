package com.erp.dashboard.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.erp.dashboard.model.api.AgentServiceFee;

@Transactional
@Repository
public class PRCommissionDao implements IPRCommissionTempDao{
	private static Logger logger = LogManager.getLogger(PRCommissionDao.class);
	
	@PersistenceContext	
	private EntityManager entityManager;	
	
	public void insertPRCommissionTempList(List<AgentServiceFee> agentServiceFees) {
		StringBuilder str = new StringBuilder();
		str.append(" insert into XXKE_PR_COMMISSION_TEMP( ");
		str.append(" 	category_code ");
		str.append("  	, description ");
		str.append("  	, quantity ");
		str.append("  	, unit_price ");
		str.append("  	, transaction_date ");
		str.append("  	, vendor_code ");
		str.append("  	, vendor_site_code ");
		str.append("  	, expense ");
		str.append("  	, cost_center ");
		str.append("  	, group_id ");
		str.append("  	, ref_no ");
		str.append("  	, segmen1 ");
		str.append("  )values( ");
		str.append(" 	:category_code ");
		str.append("  	, :description ");
		str.append("  	, :quantity ");
		str.append("  	, :unit_price ");
		str.append("  	, to_date(:transaction_date, 'DD-MON-YY') ");
		str.append("  	, :vendor_code ");
		str.append("  	, :vendor_site_code ");
		str.append("  	, :expense ");
		str.append("  	, :cost_center ");
		str.append("  	, :group_id ");
		str.append("  	, :ref_no ");
		str.append("  	, :segmen1 ");
		str.append(" ) ");
		
		for(AgentServiceFee agentServiceFee : agentServiceFees) {
			entityManager.createNativeQuery(str.toString())
	                .setParameter("category_code", agentServiceFee.getCategory_code())
	                .setParameter("description", agentServiceFee.getDescription())
	                .setParameter("quantity", agentServiceFee.getQuantity())
	                .setParameter("unit_price", agentServiceFee.getUnit_price())
	                .setParameter("transaction_date", agentServiceFee.getTransaction_date())
	                .setParameter("vendor_code", agentServiceFee.getVendor_code())
	                .setParameter("vendor_site_code", agentServiceFee.getVendor_site_code())
	                .setParameter("expense", agentServiceFee.getExpense())
	                .setParameter("cost_center", agentServiceFee.getCost_center())
	                .setParameter("group_id", agentServiceFee.getGroup_id())
	                .setParameter("ref_no", agentServiceFee.getRef_no())
	                .setParameter("segmen1", agentServiceFee.getSegmen1())
	                .executeUpdate();
		}
	}
}
