package com.fpe.statsTrader.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;

import com.fpe.statsTrader.entity.Trader;

@SessionScoped
@ManagedBean
@Component
public class CheckParams {
	
	private Trader thisTrader;
	
	public CheckParams() {
		
	}

	public Trader getThisTrader() {
		return thisTrader;
	}

	public void setThisTrader(Trader thisTrader) {
		thisTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
		this.thisTrader = thisTrader;
	}
	
	//si la cuenta no existe, no debe poder visitar esta página
	public String checkTraderExists() {
		Trader thisTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
		if (thisTrader == null) {
			return "login?faces-redirect=true";
		} else {
			return null;
		}
	}
	
	//Si la cuenta ya existe no debe poder acceder a crearcuenta
	public String checkTraderAlreadyExists() {
		Trader thisTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
		if (thisTrader != null) {
			return "modificarcuenta?faces-redirect=true";
		} else {
			return null;
		}
	}

}
