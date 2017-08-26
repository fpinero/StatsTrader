package com.fpe.statsTrader.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;

@ManagedBean
@Component
@RequestScoped
public class CerrarSesion {
	
	public CerrarSesion() {
		
	}
	
	public String cierraSesion() {
		//establece el param thisTrader a null y redirige a la pag de login
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("thisTrader", null);
		return "login?faces-redirect=true";
	}
	
	public String noCierraSesion() {
		//simplemente redirige a la pagina de content
		return "content?faces-redirect=true";
	}
	

}
