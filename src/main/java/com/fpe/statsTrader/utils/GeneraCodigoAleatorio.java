package com.fpe.statsTrader.utils;

import javax.faces.bean.ManagedBean;

import org.springframework.stereotype.Component;

@Component
@ManagedBean
public class GeneraCodigoAleatorio {
	
	public GeneraCodigoAleatorio() {
		
	}
	
	public String giveMetheCode() {
		
		int numeroAleatorio = (int) Math.round(Math.random()*999);
		numeroAleatorio = numeroAleatorio * 4;
		String SnumeroAleatorio = String.valueOf(numeroAleatorio);
		System.out.println("Número aleatorio generado: " + SnumeroAleatorio);
		return SnumeroAleatorio;
		
	}

}
