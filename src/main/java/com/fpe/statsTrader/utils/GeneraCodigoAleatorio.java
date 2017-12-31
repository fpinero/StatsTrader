package com.fpe.statsTrader.utils;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.fpe.statsTrader.jpa.CreaCuentaTraderVerificado;

//@Component
@ManagedBean
@SessionScoped
public class GeneraCodigoAleatorio implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4300932733619303547L;
	private String codigoVerificacion;
	private boolean rendered = false;
	
	public GeneraCodigoAleatorio() {
		
	}
	
	public void giveMetheCode() {
		
		String codigo = String.valueOf((long)(Math.random() * 10000000) +1);
        codigo = codigo.substring(0, 5);
		System.out.println("Número aleatorio generado: " + codigo);
		setCodigoVerificacion(codigo);
	}
	
	
	public boolean getRendered() {
		System.out.println("rendered esta ahora mismo a " + rendered);
		return rendered;
	}

	public void enviaCodigoPorEmail(String email, String user) {
		
		
		try {
			System.out.println("enviando email con codigo de verificacion a " + email);
			SimpleMail sendEmail = new SimpleMail();
			String textoEmail = "StatsTrader codigo de verificacion para " + user + "\n\n"
                    + "Codigo:" + this.getCodigoVerificacion() + "\n\n" + "Por favor, indique este codigo en la casilla de verificacion"
                    + "\n\n\n" + "Si no ha solicitado el registro en StatsTrader, por favor ignore este email "
                    + "y disculpe las molestias.";
			System.out.println("Texto mensaje:\n" + textoEmail);
			sendEmail.send(email, "Statstrader codigo de verificacion.", textoEmail);	
			System.out.println("email enviado...");
			showMessageExito(email);
			
			System.out.println("-----> Estableciendo rendered a true para mostrar el panel "
					+ "para introducir el cod. de verificacion enviado");
			rendered = true;
			
		} catch (Exception e) {
			System.out.println("Excepción enviando codigo de verificación: " + codigoVerificacion +" a " + email);
			showMessageNoExito(email);
		}
		
	}
	
	public String verificaCodigo(String userCode) {
		
		System.out.println("codigo introducido por el usuario " + userCode);
		if (userCode.equals(this.getCodigoVerificacion())){
			System.out.println("Código correctamente verificado");
			//añadamos el trader verificado a la BD
			CreaCuentaTraderVerificado creaCuentaTraderVerificado = new CreaCuentaTraderVerificado();
			creaCuentaTraderVerificado.saveTrader();
			return "creacuentaverificada?faces-redirect=true";
		} else {
			System.out.println("Código incorrectamente verificado");
			showMessageCódigoInvalido();
			return null;
		}
		
	}
	
	public String getCodigoVerificacion() {
		return codigoVerificacion;
	}

	public void setCodigoVerificacion(String codigoVerificacion) {
		this.codigoVerificacion = codigoVerificacion;
	}
	
	public void showMessageExito(String email) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Éxito!", "Email enviado a " + email));
    }
	
	public void showMessageNoExito(String email) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Error!", "No se pudo enviar el email a " + email));
    }
	
	public void showMessageCódigoInvalido() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Error!", "El código introducido es erroneo"));
    }

}
