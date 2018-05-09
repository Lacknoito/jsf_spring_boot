package cop.erp.procurement.controller;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import cop.erp.procurement.domain.Product;
import cop.erp.procurement.model.CartModel;
import cop.erp.procurement.utils.ProcurementConstant;
import cop.erp.procurement.utils.ProcurementUtils;

@RequestScope
@Component(value = "cartController")
public class CartController {
	private static Logger logger = LogManager.getLogger(CartController.class);
	
	@Autowired
	private CartModel cartModel;
	
	private Product productEdit;
	
	public void onload() {
		try {
			cartModel.setProducts(new ArrayList<>());
			cartModel.getProducts().add(new Product("Personal Computer/HP/Model 280", "Personal Computer/HP/Model 280", new BigDecimal(500), 1));
			cartModel.getProducts().add(new Product("Personal Computer/HP/Model 280", "Personal Computer/HP/Model 280", new BigDecimal(2500), 1));
			cartModel.getProducts().add(new Product("Personal Computer/HP/Model 280", "Personal Computer/HP/Model 280", new BigDecimal(3500), 1));
			cartModel.getProducts().add(new Product("Personal Computer/HP/Model 280", "Personal Computer/HP/Model 280", new BigDecimal(25099.9), 1));
			cartModel.getProducts().add(new Product("Personal Computer/HP/Model 280", "Personal Computer/HP/Model 280", new BigDecimal(10100), 1));
		}catch (Exception e) {
			logger.error("CartController onload : " + e.getMessage(), e);
		}
	}
	
	public void onCartQuantityUp() {
		try {
			productEdit.setQuantity(ProcurementUtils.actionNumber(productEdit.getQuantity(), 1, ProcurementConstant.CAL_ACTION.PLUS));
		}catch (Exception e) {
			logger.error("CartController onCartQuantityUp : " + e.getMessage(), e);
		}
	}

	public Product getProductEdit() {
		return productEdit;
	}

	public void setProductEdit(Product productEdit) {
		this.productEdit = productEdit;
	}
}
