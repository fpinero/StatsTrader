package com.fpe.statsTrader;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.SessionFactory;

// Clase para definir las variables globales que se inicializan 
// en WebApplicationInitializar sino están ya inicianilazadas

//@Component
@ManagedBean
@SessionScoped
public class GlobalVars {
	
	public static SessionFactory factory;
	public static String version;

}
