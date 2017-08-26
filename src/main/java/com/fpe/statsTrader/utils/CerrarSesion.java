package com.fpe.statsTrader.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;

import com.fpe.statsTrader.entity.Trader;

@ManagedBean
@Component
@RequestScoped
public class CerrarSesion {
	
	//Injecto el bean trader
	@ManagedProperty(value="#{trader}")
	Trader traderBean;
	
	public CerrarSesion() {
		
	}
	
	//el getter NO es obligatorio para el bean injection
	public Trader getTraderBean() {
		return traderBean;
	}

	//el setter Sí es obligado para q funcione el bean injection 
	public void setTraderBean(Trader traderBean) {
		this.traderBean = traderBean;
	}

	public String cierraSesion() {
		//establece el param thisTrader a null y redirige a la pag de login
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("thisTrader", null);
		traderBean.destruyeThisTrader();
		return "login?faces-redirect=true";
	}
	
	public String noCierraSesion() {
		//simplemente redirige a la pagina de content
		return "content?faces-redirect=true";
	}
	

}
