package com.fpe.statsTrader;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.hibernate.cfg.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.fpe.statsTrader.entity.Trader;

public class WebApplicationInitializer implements org.springframework.web.WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(WebMvcConfig.class);
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", 
                new DispatcherServlet(rootContext));
        registration.addMapping("/");
        registration.setLoadOnStartup(1);
        checkGlobalVarInit();

	}
	
	private void checkGlobalVarInit() {
		
		System.out.println("comprobando si las variables globales están inicializadas...");
		
		if (GlobalVars.factory == null) {
			System.out.println(">>> no lo están");
			System.out.println(">>> inicializando GlobalVars.factory");
			
			GlobalVars.factory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Trader.class) /* Aquí habrá q ir añadiendo las demás Entities */
					.buildSessionFactory();
		}
		
		System.out.println("Variables globales inicializadas...");
		
	}

}