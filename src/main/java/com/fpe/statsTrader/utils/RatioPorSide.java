package com.fpe.statsTrader.utils;

import java.util.Date;

public class RatioPorSide {
	
	int numeroOpesLargo = 0;
	int numeroBuenasLargo = 0;
	double ratioOpesLargo = 0d;
	int numeroOpesCorto = 0;
	int numeroBuenasCorto = 0;
	double ratioOpesCorto = 0d;
	
	public RatioPorSide() {
		
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

	public RatioPorSide obtenRatioPorSideLargoAndCorto(Date desdeFecha, Date hastaFecha) {
		
		//crea el objeto RatioSideLargo 
		RatioSideLargo ratioSideLargo = new RatioSideLargo();
		ratioSideLargo = ratioSideLargo.generaRatioOpesLargo(desdeFecha, hastaFecha);
		this.numeroBuenasLargo = ratioSideLargo.getNumeroBuenasLargo();
		this.numeroOpesLargo = ratioSideLargo.getNumeroOpesLargo();
		this.ratioOpesLargo = ratioSideLargo.getRatioOpesLargo();
		
		//crea el objeto RatioSideCorto
		RatioSideCorto ratioSideCorto = new RatioSideCorto();
		ratioSideCorto = ratioSideCorto.generaRatioOpesCorto(desdeFecha, hastaFecha);
		this.numeroBuenasCorto = ratioSideCorto.getNumeroBuenasCorto();
		this.numeroOpesCorto = ratioSideCorto.getNumeroOpesCorto();
		this.ratioOpesCorto = ratioSideCorto.getRatioOpesCorto();
		
		return this;
		
	}
	
	@Override
	public String toString() {
		return "RatioPorSide [numeroOpesLargo=" + numeroOpesLargo + ", numeroBuenasLargo=" + numeroBuenasLargo
				+ ", ratioOpesLargo=" + ratioOpesLargo + ", numeroOpesCorto=" + numeroOpesCorto + ", numeroBuenasCorto="
				+ numeroBuenasCorto + ", ratioOpesCorto=" + ratioOpesCorto + "]";
	}
	
	

}
