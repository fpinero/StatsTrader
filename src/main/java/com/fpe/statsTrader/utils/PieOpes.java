package com.fpe.statsTrader.utils;

import java.sql.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

import com.fpe.statsTrader.jpa.QueryPieOpes;

@Component
@ManagedBean
@ViewScoped
public class PieOpes {
	
	int opesBuenas;
	int opesMalas;
	int opesBe;
	
	public PieOpes() {
		
	}
	
	public PieOpes buscaOpesPie(Date fechaInicio, Date fechaFinal) {
		
		QueryPieOpes queryPieOpes = new QueryPieOpes();
		PieOpes pieOpes = queryPieOpes.buscaOPesBMB(fechaInicio, fechaFinal);
		this.opesBuenas = pieOpes.getOpesBuenas();
		this.opesMalas = pieOpes.getOpesMalas();
		this.opesBe = pieOpes.getOpesBe();
		
		return this;
		
	}
	
	public int getOpesBuenas() {
		return opesBuenas;
	}

	public void setOpesBuenas(int opesBuenas) {
		this.opesBuenas = opesBuenas;
	}

	public int getOpesMalas() {
		return opesMalas;
	}

	public void setOpesMalas(int opesMalas) {
		this.opesMalas = opesMalas;
	}

	public int getOpesBe() {
		return opesBe;
	}

	public void setOpesBe(int opesBe) {
		this.opesBe = opesBe;
	}
	
	

}
