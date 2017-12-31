package com.fpe.statsTrader.utils;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.fpe.statsTrader.entity.Trader;
import com.fpe.statsTrader.jpa.UpdateDatosCuentaTrader;

//@Component
@ManagedBean
@SessionScoped
public class ModificaTrader implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7471867681769525337L;
	@ManagedProperty(value="#{trader}")
	Trader thisTrader;
	
	public ModificaTrader() {
		
	}

	public Trader getThisTrader() {
		return thisTrader;
	}

	public void setThisTrader(Trader thisTrader) {
		this.thisTrader = thisTrader;
	}
	
	public String modificaThisTrader() {
		System.out.println("Nuevos datos del trader=" + thisTrader.toString());
		UpdateDatosCuentaTrader udct = new UpdateDatosCuentaTrader();
		boolean exito = udct.updateTrader(thisTrader);
		if (exito) {
			showMessage("Exito!! Datos de la cuenta actualizados correctamente.");
		}else {
			showMessage("Error!! No ha sido posible modificar los datos de la cuenta.");
		}
		
		return null;
	}
	
	public void showMessage(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg, msg));
    }

}
