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

import org.springframework.stereotype.Component;

import com.fpe.statsTrader.entity.Trader;
import com.fpe.statsTrader.entity.TradersOpes;
import com.fpe.statsTrader.jpa.QueryListaOpesTrader;

@ManagedBean
@Component
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
		fechaFinal = fechaHoy.getTime();
		
		fechaHoy.set(Calendar.DAY_OF_MONTH, 1);
		fechaInicial = fechaHoy.getTime();
		
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
		
		Trader currentTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
		QueryListaOpesTrader qlot = new QueryListaOpesTrader();
		tradersOpes = qlot.getTradersOpes(currentTrader, fechaInicial, fechaFinal);
		
		//provisional
//		System.out.println(">>>>> operaciones del trader logueado <<<<<<");
//		for (TradersOpes to : tradersOpes){
//			System.out.println(to);
//		}
		
	}
	
	public void verificaSiHayMsgExito() {

		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("inputOpeMsgExito") != null) {
			String msg = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("inputOpeMsgExito");
			showMessage(msg);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("inputOpeMsgExito", null);
		}

	}
	
	public void showMessage(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(msg, msg));
	}
	

}
