package com.fpe.statsTrader.utils;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

import com.fpe.statsTrader.jpa.QueryRatioSideLargos;

@Component
@ManagedBean
@SessionScoped
public class RatioSideLargo {
	
	int numeroOpesLargo = 0;
	int numeroBuenasLargo = 0;
	double ratioOpesLargo = 0d;
	
	public RatioSideLargo() {
		
	}

	public int getNumeroOpesLargo() {
		return numeroOpesLargo;
	}

	public void setNumeroOpesLargo(int numeroOpesLargo) {
		this.numeroOpesLargo = numeroOpesLargo;
	}

	public int getNumeroBuenasLargo() {
		return numeroBuenasLargo;
	}

	public void setNumeroBuenasLargo(int numeroBuenasLargo) {
		this.numeroBuenasLargo = numeroBuenasLargo;
	}

	public double getRatioOpesLargo() {
		return ratioOpesLargo;
	}

	public void setRatioOpesLargo(double ratioOpesLargo) {
		this.ratioOpesLargo = ratioOpesLargo;
	}
	
	public RatioSideLargo generaRatioOpesLargo(Date desdeFecha, Date hastaFecha){
		
		//llama a la query que busca las operaciones buenas en largo dado el rango de fechas especificado
		QueryRatioSideLargos queryRatioSideLargos = new QueryRatioSideLargos();
		RatioSideLargo tmpRatioSideLargo = queryRatioSideLargos.obtenRatioSideOpesLargo(desdeFecha, hastaFecha);
		this.numeroBuenasLargo = tmpRatioSideLargo.getNumeroBuenasLargo();
		this.numeroOpesLargo = tmpRatioSideLargo.getNumeroOpesLargo();
		this.ratioOpesLargo = tmpRatioSideLargo.getRatioOpesLargo();
		return this;
	}

	@Override
	public String toString() {
		return "RatioSideLargo [numeroOpesLargo=" + numeroOpesLargo + ", numeroBuenasLargo=" + numeroBuenasLargo
				+ ", ratioOpesLargo=" + ratioOpesLargo + "]";
	}

	
	
}
