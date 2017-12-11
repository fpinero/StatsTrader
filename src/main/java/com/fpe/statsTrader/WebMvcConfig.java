package com.fpe.statsTrader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@ComponentScan("com.fpe.*")
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Bean
    public UrlBasedViewResolver urlBasedViewResolver() {
        
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/faces/");
        resolver.setSuffix(".xhtml");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

}
