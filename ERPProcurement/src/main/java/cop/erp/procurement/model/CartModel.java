package cop.erp.procurement.model;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import cop.erp.procurement.domain.Product;

@SessionScope
@Component(value = "cartModel")
public class CartModel {
	List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
