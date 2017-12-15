package com.fpe.statsTrader.utils;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import com.fpe.statsTrader.jpa.QueryEstadisticas15m;

@Component
@ManagedBean
@RequestScoped
public class Patron15m {
	
	String patron;
	int vecesNegociado;
	int numeroStops;
	int numeroBuenas;
	int numeroBe;
	
	public Patron15m() {
		
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
	
	public Patron15m obtenDatosParaEstePatron15m(String patron15, String side, Date fechaInicial, Date fechaFinal) {
		//lanzar la query que obtiene los datos para este patron y esta side
		//y restornar este objeto
		QueryEstadisticas15m queryEstadisticas15m = new QueryEstadisticas15m();
		Patron15m tmpPatron15m = queryEstadisticas15m.obtenDatosDeStse15m(patron15, side, fechaFinal, fechaFinal); 
		this.patron = tmpPatron15m.getPatron();
		this.vecesNegociado = tmpPatron15m.getVecesNegociado();
		this.numeroStops = tmpPatron15m.getNumeroStops();
		this.numeroBuenas = tmpPatron15m.getNumeroBuenas();
		this.numeroBe = tmpPatron15m.getNumeroBe();
		
		System.out.println("this.Patron15m=" + this.toString());
		return this;
	}

	@Override
	public String toString() {
		return "Patron15m [patron=" + patron + ", vecesNegociado=" + vecesNegociado + ", numeroStops=" + numeroStops
				+ ", numeroBuenas=" + numeroBuenas + ", numeroBe=" + numeroBe + "]";
	}
	
	

}
