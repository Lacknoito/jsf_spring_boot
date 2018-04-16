package com.erp.dashboard.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;

public class ERPUtils {
	public static String BANGKOK_SAME_DAY = "BSD";
	public static String DISTRIBUTION_CENTER = "DC";
	public static String FRANCHISE_KERRYEXPRESS_SHOP = "FC/KE";
	public static String REGIONAL_PARCEL_SHOP = "UPC";

	public static String FIX_FLEX_VALUE_SET_ID = "1014995";
	public static String BANGKOK_SAME_DAY_CODE = "181002";
	
	public static interface REGION {
		public static final String V_BKK = "101";
		public static final String V_GREATER = "102";
		public static final String V_CENTRAL = "103";
		public static final String V_EAST = "104";
		public static final String V_NORTH = "105";
		public static final String V_NORTHEAST = "106";
		public static final String V_SOUTH = "107";
		public static final String V_SAMZONE = "108";
		public static final String V_DCSP = "109";
		public static final String V_BSD = "181";
		public static final String T_BKK = "BKK";
		public static final String T_GREATER = "GREATER";
		public static final String T_CENTRAL = "CENTRAL";
		public static final String T_EAST = "EAST";
		public static final String T_NORTH = "NORTH";
		public static final String T_NORTHEAST = "NORTHEAST";
		public static final String T_SOUTH = "SOUTH";
		public static final String T_SAMZONE = "SAMZONE";
		public static final String T_DCSP = "DCSP";
		public static final String T_BSD = "BSD";
	}

	public static String SIMPLE_DATE_FORMAT = "dd/MM/yyyy";
	
	public static boolean collectionIsEmpty(@SuppressWarnings("rawtypes") List list) {
		if(list == null
				|| list.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static Date convertStringToDateFormat(String dateStr, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = new Date();
		try {
			date = formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	public static String convertDateToStringFormat(Date date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		String reportDate = df.format(date);
		
		return reportDate;
	}
	
	public static void setParameterByMap(Map<String, Object> param, Query query) {
		for (Entry<String, Object> entry : param.entrySet()) {
			if (entry.getValue() instanceof Collection<?>) {
				query.setParameterList(entry.getKey(), (Collection) entry.getValue());
			} else {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
	}
}
