package com.fpe.statsTrader.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;

import com.fpe.statsTrader.entity.Trader;
import com.fpe.statsTrader.entity.TradersOpes;
import com.fpe.statsTrader.jpa.QueryListaOpesTrader;

@ManagedBean
@Component
public class ConsultaOpesHelper {
	
	private List<TradersOpes> tradersOpes;
	
	public ConsultaOpesHelper() {
		
	}

	@PostConstruct
	public void init(){
	
		tradersOpes = new ArrayList<>();
	}
	
	public List<TradersOpes> getTradersOpes() {
		return tradersOpes;
	}
	
//	public String getMyFormattedDate(int index) {
//		Date fechaThisOpe = tradersOpes.iterator().
//        return new SimpleDateFormat("dd-MM-yyyy").format(myDate);
//    }
	
	public void loadTradersOpes() {
		
		Trader currentTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
		QueryListaOpesTrader qlot = new QueryListaOpesTrader();
		tradersOpes = qlot.getTradersOpes(currentTrader);
		
		//provisional
		System.out.println(">>>>> operaciones del trader logueado <<<<<<");
		for (TradersOpes to : tradersOpes){
			System.out.println(to);
		}
		
	}
	

}
