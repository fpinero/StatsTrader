package com.fpe.statsTrader.utils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;

import com.fpe.statsTrader.entity.Trader;

@SessionScoped
@ManagedBean
@Component
public class CheckParams {
	
	private Trader thisTrader;
	
	@ManagedProperty(value="#{trader}")
	Trader beanTrader;
	
	public CheckParams() {
		
	}

	public Trader getThisTrader() {
		return thisTrader;
	}
	
	public void setBeanTrader(Trader beanTrader) {
		this.beanTrader = beanTrader;
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
	
	//Si la cuenta ha sido correctamente creada destruimos el parametro thisTrader y reenviamos a login
		public String checkTraderCorrectamenteCreado() {
			System.out.println(">>>>> Comprobando si thisTrader existe en checkTraderCorrectamenteCreado");
			Trader thisTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
			if (thisTrader != null) {
				System.out.println("thisTrader no es null.. \n" + thisTrader.toString() + "\n" + 
							"Procedemos a establecerlo a null");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("thisTrader", null);
				beanTrader.destruyeThisTrader();
				showMessage();
				return "login?faces-redirect=true";
			} else {
				return null;
			}
		}
		
		public void showMessage() {
			System.out.println("enviando el mensaje de exito al crear la cuenta...");
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Cuenta de usuario creada exitosamente!", "La cuenta de usuario ha sido creada correctamente"));
	    }
	

}
