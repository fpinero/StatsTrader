package com.fpe.statsTrader.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.PieChartModel;
import org.springframework.stereotype.Component;

@Component
@RequestScoped
@ManagedBean
public class EstadisticasHelper {
	
	private Calendar fechaHoy;
	private Date fechaInicial;
	private Date fechaFinal;
	
	private PieChartModel pieModel1;
	
	private TraderRatio traderRatio;
	
	public EstadisticasHelper() {
		
	}
	
	@PostConstruct
	public void init() {
		
		fechaHoy = GregorianCalendar.getInstance();
		try {
			System.out.println("...Verificando si existe el atributo de sesión fechaFinConsultaEstadisticas");
			Date fechaFinPrevia = (Date) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("fechaFinConsultaEstadisticas");
			if (fechaFinPrevia != null) {
				fechaFinal = fechaFinPrevia;
			} else {
				System.out.println("...Existía el atributo pero era nulo.");
				fechaFinal = fechaHoy.getTime();
			}
			System.out.println("...Existía el atributo fechaFinConsultaOpes con valor: "+ fechaFinPrevia);
		} catch (Exception e) {
			System.out.println("Excepción intentando obtener el atributo fechaIniConsultaEstadisticas en el @PostConstruct\n" + e);
			fechaFinal = fechaHoy.getTime();
			System.out.println("...Se establece fechaInicial a: " + fechaFinal);
		}
		System.out.println("...fechaFinal=" + fechaFinal);
		
		fechaHoy.set(Calendar.DAY_OF_MONTH, 1);
		
		try {
			System.out.println("...Verificando si existe el atributo de sesión fechaIniConsultaEstadisticas");
			Date fechaIniPrevia = (Date) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("fechaIniConsultaEstadisticas");
			if (fechaIniPrevia != null) {
				fechaInicial = fechaIniPrevia;
			} else {
				System.out.println("...Existía el atributo pero era nulo.");
				fechaInicial = fechaHoy.getTime();
			}
			System.out.println("...Existía el atributo fechaIniConsultaEstadisticas con valor: "+ fechaIniPrevia);
		}catch(Exception e) {
			System.out.println("Excepción intentando obtener el atributo fechaIniConsultaEstadisticas en el @PostConstruct\n" + e);
			fechaInicial = fechaHoy.getTime();
			System.out.println("...Se establece fechaInicial a: " + fechaInicial);
		}
		System.out.println("...fechaInicial=" + fechaInicial);
		
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		
		System.out.println("....Estableciendo atributo de sesion fechaIniConsultaEstadisticas=" + fechaInicial);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fechaIniConsultaEstadisticas",
				fechaInicial);
		
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		
		System.out.println("....Estableciendo atributo de sesion fechaFinConsultaEstadisticas=" + fechaFinal);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fechaFinConsultaEstadisticas",
				fechaFinal);
		
		this.fechaFinal = fechaFinal;
	}
	
	public PieChartModel getPieModel1() {
//		System.out.println("...en getPieModel1");
		createPie();
		System.out.println("pieModel1.getTitle()=" + pieModel1.getTitle());
		return pieModel1;
	}
	
	public void createPie() {
		
		System.out.println("...en createPie");
		
		pieModel1 = new PieChartModel();
		PieOpes thePie = new PieOpes().buscaOpesPie(fechaInicial, fechaFinal);
		
//		System.out.println("thePie.getOpesBuenas()=" + thePie.getOpesBuenas());
		pieModel1.set("Buenas", thePie.getOpesBuenas());
//		System.out.println("thePie.getOpesMalas()=" + thePie.getOpesMalas());
		pieModel1.set("Stops", thePie.getOpesMalas());
//		System.out.println("thePie.getOpesBe()=" + thePie.getOpesBe());
		pieModel1.set("BreakEven", thePie.getOpesBe());
		if (thePie.getOpesBuenas() == 0 && thePie.getOpesMalas() == 0 && thePie.getOpesBe() == 0) {
			pieModel1.setTitle("No hay operaciones en el rango de fechas");
		} else {
			pieModel1.setTitle("Relación Resultados Operaciones");
		}
		pieModel1.setLegendPosition("w");
				
	}

	public TraderRatio getTraderRatio() {
		createTraderRatio();
		return traderRatio;
	}
	
	public void createTraderRatio() {
		traderRatio = new TraderRatio().obtenDatosRatio(fechaInicial, fechaFinal);
		
	}

}
