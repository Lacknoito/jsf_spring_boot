package cop.erp.procurement.utils;

import java.math.BigDecimal;

public class ProcurementUtils {
	public static Integer actionNumber(Integer integer, Integer number, String action) {
		if(integer == null)
			integer = 0;
		
		if(number == null)
			number = 0;
		
		return actionNumber(new BigDecimal(integer), new BigDecimal(number), action).intValue();
	}
	
	public static BigDecimal actionNumber(BigDecimal integer, Integer number, String action) {
		if(number == null)
			number = 0;
		
		return actionNumber(integer, new BigDecimal(number), action);
	}
	
	public static BigDecimal actionNumber(BigDecimal integer, BigDecimal number, String action) {
		if(integer == null)
			integer = BigDecimal.ZERO;
		
		if(ProcurementConstant.CAL_ACTION.PLUS.equals(action)) {
			return integer.add(number);
		}else if(ProcurementConstant.CAL_ACTION.MINUS.equals(action)) {
			return integer.subtract(number);
		}else if(ProcurementConstant.CAL_ACTION.MULTIPLY.equals(action)) {
			return integer.multiply(number);
		}else {
			return integer;
		}
	}
}
