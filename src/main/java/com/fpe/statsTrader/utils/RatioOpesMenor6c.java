package com.fpe.statsTrader.utils;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.fpe.statsTrader.jpa.QueryRatioOpesMenor6c;

@ManagedBean
@RequestScoped
public class RatioOpesMenor6c implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3215017907771173384L;
	
	private int numeroTrades;
	private double ratio;
	private int tradesBuenos;
	private int tradesStops;
	private int tradesLargos;
	private int tradesCortos;
	private double brutoGenerado;
	private double netoGenerado;
	
	public RatioOpesMenor6c() {
		
	}

	public int getNumeroTrades() {
		return numeroTrades;
	}

	public void setNumeroTrades(int numeroTrades) {
		this.numeroTrades = numeroTrades;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public int getTradesBuenos() {
		return tradesBuenos;
	}

	public void setTradesBuenos(int tradesBuenos) {
		this.tradesBuenos = tradesBuenos;
	}

	public int getTradesStops() {
		return tradesStops;
	}

	public void setTradesStops(int tradesStops) {
		this.tradesStops = tradesStops;
	}

	public int getTradesLargos() {
		return tradesLargos;
	}

	public void setTradesLargos(int tradesLargos) {
		this.tradesLargos = tradesLargos;
	}

	public int getTradesCortos() {
		return tradesCortos;
	}

	public void setTradesCortos(int tradesCortos) {
		this.tradesCortos = tradesCortos;
	}
	
	public double getBrutoGenerado() {
		return brutoGenerado;
	}

	public void setBrutoGenerado(double brutoGenerado) {
		this.brutoGenerado = brutoGenerado;
	}

	public double getNetoGenerado() {
		return netoGenerado;
	}

	public void setNetoGenerado(double netoGenerado) {
		this.netoGenerado = netoGenerado;
	}

	public RatioOpesMenor6c obtenRatioOpesMenor6c(Date desdeFecha, Date hastaFecha) {
		
		//llama a la query que busca las operaciones menores de 6c dado el rango de fechas especificado
		QueryRatioOpesMenor6c queryRatioOpesMenor6c = new QueryRatioOpesMenor6c();
		RatioOpesMenor6c tmpRatioOpesMenor6c = queryRatioOpesMenor6c.obtenRatioOpesMenor6c(desdeFecha, hastaFecha);
		
		//establecer las propiedades de esta clase con el objeto recibido de la query y retornar este objeto
		this.numeroTrades = tmpRatioOpesMenor6c.getNumeroTrades();
		this.ratio = tmpRatioOpesMenor6c.getRatio();
		this.tradesBuenos = tmpRatioOpesMenor6c.getTradesBuenos();
		this.tradesStops = tmpRatioOpesMenor6c.getTradesStops();
		this.tradesLargos = tmpRatioOpesMenor6c.getTradesLargos();
		this.tradesCortos = tmpRatioOpesMenor6c.getTradesCortos();
		this.brutoGenerado = tmpRatioOpesMenor6c.getBrutoGenerado();
		this.netoGenerado = tmpRatioOpesMenor6c.getNetoGenerado();
		
		return this;
		
	}

	@Override
	public String toString() {
		return "RatioOpesMenor6c [numeroTrades=" + numeroTrades + ", ratio=" + ratio + ", tradesBuenos=" + tradesBuenos
				+ ", tradesStops=" + tradesStops + ", tradesLargos=" + tradesLargos + ", tradesCortos=" + tradesCortos
				+ ", brutoGenerado=" + brutoGenerado + ", netoGenerado=" + netoGenerado + "]";
	}

	
	
	

}
