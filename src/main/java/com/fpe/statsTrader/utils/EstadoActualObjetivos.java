package com.fpe.statsTrader.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.fpe.statsTrader.jpa.QueryEstadoActualObjetivos;

//@Component
@ManagedBean
@SessionScoped
public class EstadoActualObjetivos {
	
	private double ratioMes = 0d;
	private double netoMes = 0d;
	private double netoSemana = 0d;
	
	public EstadoActualObjetivos() {
		
	}

	public double getRatioMes() {
		return ratioMes;
	}

	public void setRatioMes(double ratioMes) {
		this.ratioMes = ratioMes;
	}

	public double getNetoMes() {
		return netoMes;
	}

	public void setNetoMes(double netoMes) {
		this.netoMes = netoMes;
	}

	public double getNetoSemana() {
		return netoSemana;
	}

	public void setNetoSemana(double netoSemana) {
		this.netoSemana = netoSemana;
	}
	
	public EstadoActualObjetivos obtenEstadoActualObjetivos() {
		//no se le pasan fechas pq el ratioMes es siempre desde el 1 del mes actual hasta el día de hoy
		//el neto del mes es siempre desde el 1 del mes actual hasta el día de hoy
		//y el neto semanal es desde el Lunes de esta semana hasta el día actual
		//en la query calculará las fechas correctas a utilizar
		
		//llamar a la query y retornar el objeto
		QueryEstadoActualObjetivos qeao = new QueryEstadoActualObjetivos();
		EstadoActualObjetivos tmpEstadoActualObjetivos = new EstadoActualObjetivos();
		tmpEstadoActualObjetivos = qeao.obtenEstadoActualObjetivos();
		this.ratioMes = tmpEstadoActualObjetivos.getRatioMes();
		this.netoMes = tmpEstadoActualObjetivos.getNetoMes();
		this.netoSemana = tmpEstadoActualObjetivos.getNetoSemana();
		
		return this;
		
	}

	@Override
	public String toString() {
		return "EstadoActualObjetivos [ratioMes=" + ratioMes + ", netoMes=" + netoMes + ", netoSemana=" + netoSemana
				+ "]";
	}
	
	

}
