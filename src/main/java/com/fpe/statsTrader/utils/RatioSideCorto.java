package com.fpe.statsTrader.utils;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

import com.fpe.statsTrader.jpa.QueryRatioSideCortos;
import com.fpe.statsTrader.jpa.QueryRatioSideLargos;

@Component
@ManagedBean
@SessionScoped
public class RatioSideCorto {
	
	int numeroOpesCorto = 0;
	int numeroBuenasCorto = 0;
	double ratioOpesCorto = 0d;
	
	public RatioSideCorto() {
		
	}

	public int getNumeroOpesCorto() {
		return numeroOpesCorto;
	}

	public void setNumeroOpesCorto(int numeroOpesCorto) {
		this.numeroOpesCorto = numeroOpesCorto;
	}

	public int getNumeroBuenasCorto() {
		return numeroBuenasCorto;
	}

	public void setNumeroBuenasCorto(int numeroBuenasCorto) {
		this.numeroBuenasCorto = numeroBuenasCorto;
	}

	public double getRatioOpesCorto() {
		return ratioOpesCorto;
	}

	public void setRatioOpesCorto(double ratioOpesCorto) {
		this.ratioOpesCorto = ratioOpesCorto;
	}

	public RatioSideCorto generaRatioOpesCorto(Date desdeFecha, Date hastaFecha){
		
		//llama a la query que busca las operaciones buenas en corto dado el rango de fechas especificado
		QueryRatioSideCortos queryRatioSideCortos = new QueryRatioSideCortos();
		RatioSideCorto tmpRatioSideCorto = queryRatioSideCortos.obtenRatioSideOpesCortos(desdeFecha, hastaFecha);
		this.numeroBuenasCorto = tmpRatioSideCorto.getNumeroBuenasCorto();
		this.numeroOpesCorto = tmpRatioSideCorto.getNumeroOpesCorto();
		this.ratioOpesCorto = tmpRatioSideCorto.getRatioOpesCorto();
		return this;
	}

	@Override
	public String toString() {
		return "RatioSideCorto [numeroOpesCorto=" + numeroOpesCorto + ", numeroBuenasCorto=" + numeroBuenasCorto
				+ ", ratioOpesCorto=" + ratioOpesCorto + "]";
	}

	

	
	
}
