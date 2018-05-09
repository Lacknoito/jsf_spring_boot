package cop.erp.procurement.controller;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component(value = "templateController")
public class TemplateController {
	private static Logger logger = LogManager.getLogger(TemplateController.class);
	
	public void onPageCard() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("cart.jsf");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void onPageProducts() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("products.jsf");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
