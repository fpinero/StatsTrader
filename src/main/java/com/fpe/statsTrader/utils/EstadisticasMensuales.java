package com.fpe.statsTrader.utils;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class EstadisticasMensuales implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1336700221555170233L;
	
	private String mesAno;
	private int sharesMovidas;
	private int numeroTrades;
	private int numeroBuenas;
	private int numeroStops;
	private int numeroBreakEven;
	private double ratioMes;
	private double brutoMes;
	private double netoMes;
	
	public EstadisticasMensuales() {
		
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public int getSharesMovidas() {
		return sharesMovidas;
	}

	public void setSharesMovidas(int sharesMovidas) {
		this.sharesMovidas = sharesMovidas;
	}

	public int getNumeroTrades() {
		return numeroTrades;
	}

	public void setNumeroTrades(int numeroTrades) {
		this.numeroTrades = numeroTrades;
	}

	public int getNumeroBuenas() {
		return numeroBuenas;
	}

	public void setNumeroBuenas(int numeroBuenas) {
		this.numeroBuenas = numeroBuenas;
	}

	public int getNumeroStops() {
		return numeroStops;
	}

	public void setNumeroStops(int numeroStops) {
		this.numeroStops = numeroStops;
	}

	public int getNumeroBreakEven() {
		return numeroBreakEven;
	}

	public void setNumeroBreakEven(int numeroBreakEven) {
		this.numeroBreakEven = numeroBreakEven;
	}

	public double getRatioMes() {
		return ratioMes;
	}

	public void setRatioMes(double ratioMes) {
		this.ratioMes = ratioMes;
	}

	public double getBrutoMes() {
		return brutoMes;
	}

	public void setBrutoMes(double brutoMes) {
		this.brutoMes = brutoMes;
	}

	public double getNetoMes() {
		return netoMes;
	}

	public void setNetoMes(double netoMes) {
		this.netoMes = netoMes;
	}

	@Override
	public String toString() {
		return "EstadisticasMensuales [mesAno=" + mesAno + ", sharesMovidas=" + sharesMovidas + ", numeroTrades="
				+ numeroTrades + ", numeroBuenas=" + numeroBuenas + ", numeroStops=" + numeroStops
				+ ", numeroBreakEven=" + numeroBreakEven + ", ratioMes=" + ratioMes + ", brutoMes=" + brutoMes
				+ ", netoMes=" + netoMes + "]";
	}

	

}
