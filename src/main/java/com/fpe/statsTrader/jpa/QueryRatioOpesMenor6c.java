package com.fpe.statsTrader.jpa;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.context.FacesContext;

import org.hibernate.Session;

import com.fpe.statsTrader.GlobalVars;
import com.fpe.statsTrader.entity.Trader;
import com.fpe.statsTrader.entity.TradersOpes;
import com.fpe.statsTrader.utils.ConvertToSqlDateFormatWithQuotes;
import com.fpe.statsTrader.utils.RatioOpesMenor6c;

public class QueryRatioOpesMenor6c {
	
	private int numeroTrades = 0;
	private double ratio = 0d;
	private int tradesBuenos = 0;
	private int tradesStops = 0;
	private int tradesLargos = 0;
	private int tradesCortos = 0;
	private double brutoGenerado = 0;
	private double netoGenerado = 0;
	
	public RatioOpesMenor6c obtenRatioOpesMenor6c(Date desdeFecha, Date hastaFecha) {
		
		RatioOpesMenor6c beanRatioOpesMenor6c = new RatioOpesMenor6c();
		
		List<TradersOpes> thisTradersOpes = new ArrayList<>();
		
		Trader thisTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
		int idTrader = thisTrader.getId();
		System.out.println("...id del trader logueado en QueryRatioOpesMenor6c: " + idTrader);
		
		ConvertToSqlDateFormatWithQuotes convert = new ConvertToSqlDateFormatWithQuotes();
		
		//para no usar el = en fechaFinal le sumamos un dia y solo usamos el <
		
		Calendar c = GregorianCalendar.getInstance();
		c.setTime(hastaFecha);
		c.add(Calendar.DAY_OF_MONTH, 1); //sumamos un día
		hastaFecha = c.getTime();
		
		// Creemos la sesión
		Session session = GlobalVars.factory.getCurrentSession();
		
		try {
			
			//comencemos la transaccion
			session.beginTransaction();
			
			//Obtengamos todas las operaciones menores a 6c
			String query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.centsTrade < 6";
			System.out.println("query obtenRatio=" + query);
			
			thisTradersOpes = session.createQuery(query).getResultList();
			
			//numero total de trades realizados de menos de 6c
			if (!thisTradersOpes.isEmpty()) {
				numeroTrades = thisTradersOpes.size();
			} else {
				numeroTrades = 0;
			}
			
			System.out.println("total trades menores de 6c= " + numeroTrades);
			
			//numero de operaciones buenas
			int tmpOpesBuenas = 0;
			for (TradersOpes tr : thisTradersOpes) {
				if(tr.getResultadoTrade().equals("Bueno")) {
					tmpOpesBuenas++;
				}
			}
			tradesBuenos = tmpOpesBuenas;
			
			System.out.println("numero trades buenos menores 6c=" + tradesBuenos);
			
			//numero de operaciones stops
			int tmpOpesMalas = 0;
			for (TradersOpes tr : thisTradersOpes) {
				if(tr.getResultadoTrade().equals("Stop")) {
					tmpOpesMalas++;
				}
			}
			tradesStops = tmpOpesMalas;
			
			System.out.println("numero trades stops menores 6c=" + tradesStops);
			
			//numero de operaciones en largo
			int tmpOpesLargas = 0;
			for (TradersOpes tr : thisTradersOpes) {
				if(tr.getSideTrade().equals("Largo")) {
					tmpOpesLargas++;
				}
			}
			tradesLargos = tmpOpesLargas;
			
			System.out.println("numero trades largos menores 6c=" + tradesLargos);
			
			//numero de operaciones en corto
			int tmpOpesCortas = 0;
			for (TradersOpes tr : thisTradersOpes) {
				if(tr.getSideTrade().equals("Corto")) {
					tmpOpesCortas++;
				}
			}
			tradesCortos = tmpOpesCortas;
			
			System.out.println("numero trades cortos menores 6c=" + tradesCortos);
			
			//calculemos el ratio
			///(numeroOpesBuenas * 100) / totalOpes)
			
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			
			try {
				ratio = Double.parseDouble(df.format(((tradesBuenos * 100) / numeroTrades)).replace("," , "."));
			} catch (Exception e) {
				System.out.println("excepcion calculando el ratio en obtenRatioOpesMenor6c \n" +e);
				ratio = 0;
			}
			
			System.out.println("ratio alcanzado en trades menores 6c=" + ratio);
			
			//obtengamos el bruto generado por las operaciones
			double tmpBruto = 0d;
			for (TradersOpes tr : thisTradersOpes) {
				tmpBruto += tr.getBrutoOpe();
			}
			//System.out.println("tmpBruto=" + tmpBruto);
			try {
				brutoGenerado = Double.parseDouble(df.format(tmpBruto).replace(",", "")); //hay que quitar las , pq son miles en USA
			} catch (Exception e){
				System.out.println("excepcion parseando brutoGenerado " + tmpBruto + "en obtenRatioOpesMenor6c\n" + e);
				brutoGenerado = 0d;
			}
			
			System.out.println("bruto generado en trades menores 6c==" + ratio);
			
			//obtengamos el neto generado por las operaciones
			double tmpNeto = 0d;
			for (TradersOpes tr : thisTradersOpes) {
				tmpNeto += tr.getNetoOpe();
			}
			System.out.println("tmpNeto=" + tmpNeto);
			try {
				netoGenerado = Double.parseDouble(df.format(tmpNeto).replace(",", "")); //hay que quitar las , pq son miles en USA
			} catch(Exception e) {
				System.out.println("excepcion parseando netoGenerado " + tmpBruto + "en obtenRatioOpesMenor6c\n" + e);
				netoGenerado = 0d;
			}
			System.out.println("neto generado en trades menores 6c=" + ratio);
			
			
			
		} catch (Exception e) {
			System.out.println("Excepción en obtenRatioOpesMenor6c \n" + e);
			return null;
		} finally{
	        session.close();
	    }
		
		beanRatioOpesMenor6c.setNumeroTrades(numeroTrades);
		beanRatioOpesMenor6c.setRatio(ratio);
		beanRatioOpesMenor6c.setTradesBuenos(tradesBuenos);
		beanRatioOpesMenor6c.setTradesStops(tradesStops);
		beanRatioOpesMenor6c.setTradesLargos(tradesLargos);
		beanRatioOpesMenor6c.setTradesCortos(tradesCortos);
		beanRatioOpesMenor6c.setBrutoGenerado(brutoGenerado);
		beanRatioOpesMenor6c.setNetoGenerado(netoGenerado);
		
//		System.out.println("beanRatioOpesMenor6c=" + beanRatioOpesMenor6c.toString());
		
		return beanRatioOpesMenor6c;
				
	}

}
