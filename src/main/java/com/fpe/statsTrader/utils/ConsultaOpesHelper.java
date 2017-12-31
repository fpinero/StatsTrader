package com.fpe.statsTrader.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.fpe.statsTrader.entity.Trader;
import com.fpe.statsTrader.entity.TradersOpes;
import com.fpe.statsTrader.jpa.QueryListaOpesTrader;

@ManagedBean
//@Component
@ViewScoped
public class ConsultaOpesHelper {
	
	private List<TradersOpes> tradersOpes;
	
	private TradersOpes selectedOpe;
	
	private Calendar fechaHoy;
	
	private Date fechaInicial;
	
	private Date fechaFinal;
	
	public ConsultaOpesHelper() {
		
	}

	@PostConstruct
	public void init(){
		tradersOpes = new ArrayList<>();
		
		fechaHoy = GregorianCalendar.getInstance();
		
		try {
			System.out.println("...Verificando si existe el atributo de sesión fechaFinConsultaOpes");
			Date fechaFinPrevia = (Date) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("fechaFinConsultaOpes");
			if (fechaFinPrevia != null) {
				fechaFinal = fechaFinPrevia;
			} else {
				System.out.println("...Existía el atributo pero era nulo.");
				fechaFinal = fechaHoy.getTime();
			}
			System.out.println("...Existía el atributo fechaFinConsultaOpes con valor: "+ fechaFinPrevia);
		} catch (Exception e) {
			System.out.println("Excepción intentando obtener el atributo fechaFinConsultaOpes en el @PostConstruct\n" + e);
			fechaFinal = fechaHoy.getTime();
			System.out.println("...Se establece fechaInicial a: " + fechaFinal);
		}
		System.out.println("...fechaFinal=" + fechaFinal);
		
		fechaHoy.set(Calendar.DAY_OF_MONTH, 1);
		
		try {
			System.out.println("...Verificando si existe el atributo de sesión fechaIniConsultaOpes");
			Date fechaIniPrevia = (Date) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("fechaIniConsultaOpes");
			if (fechaIniPrevia != null) {
				fechaInicial = fechaIniPrevia;
			} else {
				System.out.println("...Existía el atributo pero era nulo.");
				fechaInicial = fechaHoy.getTime();
			}
			System.out.println("...Existía el atributo fechaIniConsultaOpes con valor: "+ fechaIniPrevia);
		}catch(Exception e) {
			System.out.println("Excepción intentando obtener el atributo fechaIniConsultaOpes en el @PostConstruct\n" + e);
			fechaInicial = fechaHoy.getTime();
			System.out.println("...Se establece fechaInicial a: " + fechaInicial);
		}
		System.out.println("...fechaInicial=" + fechaInicial);
		
		
	}
	
	public List<TradersOpes> getTradersOpes() {
		return tradersOpes;
	}
	
	public TradersOpes getSelectedOpe() {
		System.out.println("getSelectedOpe="+selectedOpe);
		return selectedOpe;
	}

	public void setSelectedOpe(TradersOpes selectedOpe) {
		this.selectedOpe = selectedOpe;
		System.out.println("setSelectedOpe="+selectedOpe);
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

	public void loadTradersOpes() {
		
		System.out.println("....Estableciendo atributo de sesion fechaIniConsultaOpes=" + fechaInicial);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fechaIniConsultaOpes",
				fechaInicial);
		System.out.println("....Estableciendo atributo de sesion fechaFinConsultaOpes=" + fechaFinal);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fechaFinConsultaOpes",
				fechaFinal);
		
		Trader currentTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
		QueryListaOpesTrader qlot = new QueryListaOpesTrader();
		
		
		
		tradersOpes = qlot.getTradersOpes(currentTrader, fechaInicial, fechaFinal);
		
		//provisional
		System.out.println(">>>>> operaciones del trader logueado <<<<<<");
		for (TradersOpes to : tradersOpes){
			System.out.println(to);
		}
		
	}
	
	public void verificaSiHayMsgExito() {

		System.out.println("Verificando en ConsultaOpesHelper si hay mensaje de éxito que mostrar");
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("inputOpeMsgExito") != null) {
			String msg = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("inputOpeMsgExito");
			showMessage(msg);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("inputOpeMsgExito", null);
		}

	}
	
	public void showMessage(String msg) {
		System.out.println("mensaje a mostrar=" + msg);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(msg, msg));
	}
	

}
