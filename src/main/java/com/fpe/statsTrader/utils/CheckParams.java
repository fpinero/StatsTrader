package com.fpe.statsTrader.utils;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.fpe.statsTrader.GlobalVars;
import com.fpe.statsTrader.entity.Trader;

@SessionScoped
@ManagedBean
//@Component
public class CheckParams implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
				//enviemos un email a fpinero@quierosertrader.com de que una nueva cuenta ha sido creada
				enviaAvisonuevaCtaCreadaEnStatsTrader(thisTrader.getNombre(), thisTrader.getUser(), thisTrader.getEmail());
				
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
		
		//Establece el parametro de sesion thisVersionApp para que esté disponible para toda la app
		//se llama a este método desde index.xhtml
		public void inicializaParamVersionApp() {
			VersionApp versionApp = new VersionApp(GlobalVars.version);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("thisVersionApp", versionApp);
			System.out.println("Estableciendo el attributo de sesión thisVersionApp a: " + versionApp);
		}
		
		//si el usuario ya está logueado, se le reenvia a content
		//en caso contrario se le reenvía a login
		public String checkIndexPage() {
			Trader thisTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
			if (thisTrader == null) {
				return "login?faces-redirect=true";
			} else {
				return "content?faces-redirect=true";
			}
		}
		
		private void enviaAvisonuevaCtaCreadaEnStatsTrader(String nombre, String user, String email) {
			
			System.out.println("Enviando email de aviso de nueva cuenta de usuario creada en StatsTrader...");
			
			try {
				SimpleMail sendEmail = new SimpleMail();
				String textoEmail = "Se ha creado una nueva cuenta en StatsTrader \n\n" + "Nombre: " + nombre + "\n\n"
	                    + "User:" + user + "\n\n" + "Email: " + email;
				System.out.println("Texto mensaje:\n" + textoEmail);
				sendEmail.send("fpinero@quierosertrader.com", "StatsTrader nueva cuenta creada.", textoEmail);	
				System.out.println("email enviado...");
				
			} catch (Exception e) {
				System.out.println("Excepción enviando codigo nueva cuenta creada en StatsTrader \n " + e);
			}
			
		}
		
		public void showMessage() {
			System.out.println("enviando el mensaje de exito al crear la cuenta...");
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Cuenta de usuario creada exitosamente!", "La cuenta de usuario ha sido creada correctamente"));
	    }
	

}
