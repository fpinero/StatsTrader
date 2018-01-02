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
import com.fpe.statsTrader.utils.RatioOpesMayor5c;

public class QueryRatioOpesMayor5c {
	
	private int numeroTrades = 0;
	private double ratio = 0d;
	private int tradesBuenos = 0;
	private int tradesStops = 0;
	private int tradesLargos = 0;
	private int tradesCortos = 0;
	
	public RatioOpesMayor5c obtenRatioOpesMayor5c(Date desdeFecha, Date hastaFecha) {
		
		RatioOpesMayor5c beanRatioOpesMayor5c = new RatioOpesMayor5c();
		
		List<TradersOpes> thisTradersOpes = new ArrayList<>();
		
		Trader thisTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
		int idTrader = thisTrader.getId();
		System.out.println("...id del trader logueado en QueryRatioOpesMayor5c: " + idTrader);
		
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
			
			//Obtengamos todas las operaciones mayores a 5c
			String query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.centsTrade > 5";
			System.out.println("query obtenRatio=" + query);
			
			thisTradersOpes = session.createQuery(query).getResultList();
			
			//numero total de trades realizados mayores 5c
			if (!thisTradersOpes.isEmpty()) {
				numeroTrades = thisTradersOpes.size();
			} else {
				numeroTrades = 0;
			}
			
			System.out.println("total trades mayores 5c= " + numeroTrades);
			
			//numero de operaciones buenas
			int tmpOpesBuenas = 0;
			for (TradersOpes tr : thisTradersOpes) {
				if(tr.getResultadoTrade().equals("Bueno")) {
					tmpOpesBuenas++;
				}
			}
			tradesBuenos = tmpOpesBuenas;
			
			System.out.println("numero trades buenos mayores 5c=" + tradesBuenos);
			
			//numero de operaciones stops
			int tmpOpesMalas = 0;
			for (TradersOpes tr : thisTradersOpes) {
				if(tr.getResultadoTrade().equals("Stop")) {
					tmpOpesMalas++;
				}
			}
			tradesStops = tmpOpesMalas;
			
			System.out.println("numero trades stops mayores 5c=" + tradesStops);
			
			//numero de operaciones en largo
			int tmpOpesLargas = 0;
			for (TradersOpes tr : thisTradersOpes) {
				if(tr.getSideTrade().equals("Largo")) {
					tmpOpesLargas++;
				}
			}
			tradesLargos = tmpOpesLargas;
			
			System.out.println("numero trades largos mayores 5c=" + tradesLargos);
			
			//numero de operaciones en corto
			int tmpOpesCortas = 0;
			for (TradersOpes tr : thisTradersOpes) {
				if(tr.getSideTrade().equals("Corto")) {
					tmpOpesCortas++;
				}
			}
			tradesCortos = tmpOpesCortas;
			
			System.out.println("numero trades cortos mayores 5c=" + tradesCortos);
			
			//calculemos el ratio
			///(numeroOpesBuenas * 100) / totalOpes)
			
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			
			try {
				ratio = Double.parseDouble(df.format(((tradesBuenos * 100) / numeroTrades)).replace("," , "."));
			} catch (Exception e) {
				System.out.println("excepcion calculando el ratio en obtenRatioOpesMayor5c \n" +e);
				ratio = 0;
			}
			
			System.out.println("ratio alcanzado en trades mayores 5c=" + ratio);
			
			
			
		} catch (Exception e) {
			System.out.println("Excepción en obtenRatioOpesMayor5c \n" + e);
			return null;
		} finally{
	        session.close();
	    }
		
		beanRatioOpesMayor5c.setNumeroTrades(numeroTrades);
		beanRatioOpesMayor5c.setRatio(ratio);
		beanRatioOpesMayor5c.setTradesBuenos(tradesBuenos);
		beanRatioOpesMayor5c.setTradesStops(tradesStops);
		beanRatioOpesMayor5c.setTradesLargos(tradesLargos);
		beanRatioOpesMayor5c.setTradesCortos(tradesCortos);
		
//		System.out.println("beanRatioOpesMayor5c=" + beanRatioOpesMayor5c.toString());
		
		return beanRatioOpesMayor5c;
				
	}

}
