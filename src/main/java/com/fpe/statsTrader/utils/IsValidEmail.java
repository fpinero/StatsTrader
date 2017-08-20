package com.fpe.statsTrader.utils;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.ComponentScan;

import com.fpe.statsTrader.jpa.QueryEmailYaEnUso;
import com.fpe.statsTrader.jpa.QueryForbidenDomains;
import com.fpe.statsTrader.jpa.QueryNombreUsuarioYaEnUso;

@ComponentScan
@ManagedBean
public class IsValidEmail {
	
	public IsValidEmail() {
		
	}
	
	public boolean isValidEmailAddress(String email) {
        
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        System.out.println("Email válido: " + m.matches());
        return m.matches();
 }
	
	public boolean isAnEmailDisposable(String email) {
		
		QueryForbidenDomains qfd = new QueryForbidenDomains();
		
		boolean emailInvalido = qfd.isEmailDisposable(email);
		
		return emailInvalido;
		
	}
	
	public boolean nombreUsuarioYaEnUso(String userName) {
		
		QueryNombreUsuarioYaEnUso qnu = new QueryNombreUsuarioYaEnUso();
		
		boolean usuarioYaEnUso = qnu.isUsernameInUse(userName);
		
		return usuarioYaEnUso;
	}
	
	public boolean EmailYaEnUso(String email) {
		
		QueryEmailYaEnUso qeu = new QueryEmailYaEnUso();
		
		boolean emailYaEnUso = qeu.isEmailInUse(email);
		
		return emailYaEnUso;
	}
	
}
