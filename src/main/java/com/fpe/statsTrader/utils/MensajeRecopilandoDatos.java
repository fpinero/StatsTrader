package com.fpe.statsTrader.utils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.stereotype.Component;


@Component
@SessionScoped
@ManagedBean
public class MensajeRecopilandoDatos {
	
	private String mensaje = "Por favor espere. Recopinanndo datos...";

	public String getMensaje() {
				showMessage(mensaje);
				return null;
	}
	
	public void muestraMsg(ComponentSystemEvent event){
		showMessage(mensaje);
	}
	
	public void showMessage(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg, msg));
    }

}
