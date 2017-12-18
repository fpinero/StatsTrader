package com.fpe.statsTrader.utils;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

import com.fpe.statsTrader.jpa.QueryEstadisticas1m;

@Component
@ManagedBean
@SessionScoped
public class Patron1m {
	
	String patron;
	int vecesNegociado;
	int numeroStops;
	int numeroBuenas;
	int numeroBe;
	
	public Patron1m() {
		
	}

	public String getPatron() {
		return patron;
	}

	public void setPatron(String patron) {
		this.patron = patron;
	}

	public int getVecesNegociado() {
		return vecesNegociado;
	}

	public void setVecesNegociado(int vecesNegociado) {
		this.vecesNegociado = vecesNegociado;
	}

	public int getNumeroStops() {
		return numeroStops;
	}

	public void setNumeroStops(int numeroStops) {
		this.numeroStops = numeroStops;
	}

	public int getNumeroBuenas() {
		return numeroBuenas;
	}

	public void setNumeroBuenas(int numeroBuenas) {
		this.numeroBuenas = numeroBuenas;
	}

	public int getNumeroBe() {
		return numeroBe;
	}

	public void setNumeroBe(int numeroBe) {
		this.numeroBe = numeroBe;
	}
	
	public Patron1m obtenDatosParaEstePatron1m(String patron1, String side, Date fechaInicial, Date fechaFinal) {
		//lanzar la query que obtiene los datos para este patron y esta side
		//y restornar este objeto
//		System.out.println("fechaInicial en obtenDatosParaEstePatron1m=" + fechaInicial);
		QueryEstadisticas1m queryEstadisticas1m = new QueryEstadisticas1m();
		Patron1m tmpPatron1m = queryEstadisticas1m.obtenDatosDeStse1m(patron1, side, fechaInicial, fechaFinal); 
		this.patron = tmpPatron1m.getPatron();
		this.vecesNegociado = tmpPatron1m.getVecesNegociado();
		this.numeroStops = tmpPatron1m.getNumeroStops();
		this.numeroBuenas = tmpPatron1m.getNumeroBuenas();
		this.numeroBe = tmpPatron1m.getNumeroBe();
		
		System.out.println("this.Patron1m=" + this.toString());
		return this;
	}

	@Override
	public String toString() {
		return "Patron1m [patron=" + patron + ", vecesNegociado=" + vecesNegociado + ", numeroStops=" + numeroStops
				+ ", numeroBuenas=" + numeroBuenas + ", numeroBe=" + numeroBe + "]";
	}
	
	

}
