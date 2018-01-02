package com.fpe.statsTrader.utils;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.fpe.statsTrader.jpa.QueryRatioOpesMayor5c;
import com.fpe.statsTrader.jpa.QueryRatioOpesMenor6c;

@ManagedBean
@RequestScoped
public class RatioOpesMayor5c implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3822426966138311425L;
	/**
	 * 
	 */
	
	private int numeroTrades;
	private double ratio;
	private int tradesBuenos;
	private int tradesStops;
	private int tradesLargos;
	private int tradesCortos;
	
	public RatioOpesMayor5c() {
		
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
	
	public RatioOpesMayor5c obtenRatioOpesMayor5c(Date desdeFecha, Date hastaFecha) {
		
		//llama a la query que busca las operaciones menores de 6c dado el rango de fechas especificado
		QueryRatioOpesMayor5c queryRatioOpesMayor5c = new QueryRatioOpesMayor5c();
		RatioOpesMayor5c tmpRatioOpesmayor5c = queryRatioOpesMayor5c.obtenRatioOpesMayor5c(desdeFecha, hastaFecha);
		
		//establecer las propiedades de esta clase con el objeto recibido de la query y retornar este objeto
		this.numeroTrades = tmpRatioOpesmayor5c.getNumeroTrades();
		this.ratio = tmpRatioOpesmayor5c.getRatio();
		this.tradesBuenos = tmpRatioOpesmayor5c.getTradesBuenos();
		this.tradesStops = tmpRatioOpesmayor5c.getTradesStops();
		this.tradesLargos = tmpRatioOpesmayor5c.getTradesLargos();
		this.tradesCortos = tmpRatioOpesmayor5c.getTradesCortos();
		
		return this;
		
	}

	@Override
	public String toString() {
		return "RatioOpesMayor5c [numeroTrades=" + numeroTrades + ", ratio=" + ratio + ", tradesBuenos=" + tradesBuenos
				+ ", tradesStops=" + tradesStops + ", tradesLargos=" + tradesLargos + ", tradesCortos=" + tradesCortos
				+ "]";
	}

	
	
	

}
