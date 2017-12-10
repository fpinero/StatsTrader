package com.fpe.statsTrader.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

@ManagedBean
@Component
@ViewScoped
public class EstadisticasHelper {
	
	private Calendar fechaHoy;
	
	private Date fechaInicial;
	
	private Date fechaFinal;
	
	public EstadisticasHelper() {
		
	}
	
	@PostConstruct
	public void init() {
		
		fechaHoy = GregorianCalendar.getInstance();
		fechaFinal = fechaHoy.getTime();
		
		fechaHoy.set(Calendar.DAY_OF_MONTH, 1);
		fechaInicial = fechaHoy.getTime();
		
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	

}
