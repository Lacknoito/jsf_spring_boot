package com.erp.dashboard;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class DefaultView extends WebMvcConfigurerAdapter {
	@Override
	  public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/pages/index.jsf");

	    super.addViewControllers(registry);
	  }
}
