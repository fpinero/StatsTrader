package com.fpe.statsTrader.utils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;

import com.fpe.statsTrader.jpa.QueryLogin;

@ManagedBean
@Component
@SessionScoped
public class CheckLogin {
	
	private String user;
	
	private String pwd;
	
	public CheckLogin() {
		
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String compruebaCredenciales() {
		
		//retorna null si es login invalido, 
		//en caso contrario retorna la pagina princiapl de la app 
		
		QueryLogin ql = new QueryLogin();
		boolean usuarioValido = ql.isUserPwdCorrecto(user, pwd);
		if (usuarioValido) {
			showMessage("Inciada sesión correctamente!");
			return "content?faces-redirect=true";
		} else {
			showMessage("Usuario/Password incorrecto!");
			return null;
		}
	}
	
	public void showMessage(String msg) {
		System.out.println("enviando el mensaje de exito o no del login");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg, msg));
    }
	

}
