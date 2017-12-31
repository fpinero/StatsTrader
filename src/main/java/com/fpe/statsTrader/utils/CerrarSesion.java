package com.fpe.statsTrader.utils;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.fpe.statsTrader.entity.Trader;

@ManagedBean
//@Component
@SessionScoped
public class CerrarSesion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7668014777768084494L;
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
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();  //esto destruye todos los parámetros de la sesión
		return "login?faces-redirect=true";  //obligatorio tras invalidar la sesión
	}
	
	public String noCierraSesion() {
		//simplemente redirige a la pagina de content
		return "content?faces-redirect=true";
	}
	

}
