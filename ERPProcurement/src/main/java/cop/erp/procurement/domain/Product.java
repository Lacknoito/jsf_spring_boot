package cop.erp.procurement.domain;

import java.math.BigDecimal;

import cop.erp.procurement.utils.ProcurementConstant;
import cop.erp.procurement.utils.ProcurementUtils;

public class Product {
	private String productCode;
	private String productDescription;
	private BigDecimal productPrice;
	private Integer quantity;
	
	public Product() {};
	
	public Product(String productCode, String productDescription, BigDecimal productPrice, Integer quantity) {
		this.productCode = productCode;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.quantity = quantity;
	}
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getTotalPrice() {
		return ProcurementUtils.actionNumber(productPrice, quantity, ProcurementConstant.CAL_ACTION.MULTIPLY);
	}
}
