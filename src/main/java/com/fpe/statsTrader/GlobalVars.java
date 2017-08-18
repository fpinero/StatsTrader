package com.fpe.statsTrader;

import javax.faces.bean.ManagedBean;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

// Clase para definir las variables globales que se inicializan 
// en WebApplicationInitializar sino están ya inicianilazadas

@Component
@ManagedBean
public class GlobalVars {
	
	public static SessionFactory factory;
	

}
