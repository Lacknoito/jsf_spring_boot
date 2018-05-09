package cop.erp.procurement;


import javax.faces.webapp.FacesServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("cop.erp.procurement")
@EnableAutoConfiguration
public class ErpProcurementApplication extends SpringBootServletInitializer {
	@Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        return new ServletRegistrationBean(servlet, "*.jsf");
    }
	
	public static void main(String[] args) {
		SpringApplication.run(ErpProcurementApplication.class, args);
	}
}
