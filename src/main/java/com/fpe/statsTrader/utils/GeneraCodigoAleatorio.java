package com.fpe.statsTrader.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

import com.fpe.statsTrader.entity.Trader;

@Component
@ManagedBean
@SessionScoped
public class GeneraCodigoAleatorio {
	
	private String codigoVerificacion;
	
	public GeneraCodigoAleatorio() {
		
	}
	
	public void giveMetheCode() {
		
		String codigo = String.valueOf((long)(Math.random() * 10000000) +1);
        codigo = codigo.substring(0, 5);
		System.out.println("Número aleatorio generado: " + codigo);
		setCodigoVerificacion(codigo);
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
		} catch (Exception e) {
			System.out.println("Excepción enviando codigo de verificación: " + codigoVerificacion +" a " + email);
		}
		
	}
	
	public String getCodigoVerificacion() {
		return codigoVerificacion;
	}

	public void setCodigoVerificacion(String codigoVerificacion) {
		this.codigoVerificacion = codigoVerificacion;
	}
	

}
