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
import com.fpe.statsTrader.utils.RatioSideCorto;

public class QueryRatioSideCortos {
	
	int numeroOpesCorto = 0;
	int numeroBuenasCorto = 0;
	double ratioOpesCorto = 0d;
	
	public RatioSideCorto obtenRatioSideOpesCortos(Date desdeFecha, Date hastaFecha) {
		
		RatioSideCorto beanRatioCorto = new RatioSideCorto();
		
		List<TradersOpes> thisTradersOpes = new ArrayList<>();
		
		Trader thisTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
		int idTrader = thisTrader.getId();
		System.out.println("...id del trader logueado en QueryRatioSideCortos: " + idTrader);
		
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
			
			//Obtengamos las operaciones buenas en corto
			String query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.resultadoTrade='Bueno' AND t.sideTrade='Corto'";
			System.out.println("query obtenRatio=" + query);
			
			thisTradersOpes = session.createQuery(query).getResultList();
			
			if (!thisTradersOpes.isEmpty()) {
				numeroBuenasCorto = thisTradersOpes.size();
			} else {
				numeroBuenasCorto = 0;
			}
			System.out.println("...numeroBuenasCorto del trader=" + numeroBuenasCorto);
			
			//Obtengamos todas las operaciones realizadas en corto
			query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.sideTrade='Corto'";
			System.out.println("query obtenRatio=" + query);
			
			thisTradersOpes = session.createQuery(query).getResultList();
			
			if (!thisTradersOpes.isEmpty()) {
				numeroOpesCorto = thisTradersOpes.size();
			} else {
				numeroOpesCorto = 0;
			}
			System.out.println("...numeroOpesCorto del trader=" + numeroOpesCorto);
			
			//hagamos el commit
			session.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("Excepción en obtenRatioSideOpesCortos \n" + e);
			return null;
		}
		
		//calculemos el ratio
		//(numeroBuenasCorto * 100) / numeroOpesCorto
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		
		if (numeroOpesCorto == 0) {
			ratioOpesCorto = 0d;
		} else {
			ratioOpesCorto = Double.parseDouble((df.format((numeroBuenasCorto * 100)/numeroOpesCorto).replace("," , ".")));
		}
		
		System.out.println("ratioOpesLargo del trader=" + ratioOpesCorto);
		
		beanRatioCorto.setNumeroBuenasCorto(numeroBuenasCorto);
		beanRatioCorto.setNumeroOpesCorto(numeroOpesCorto);
		beanRatioCorto.setRatioOpesCorto(ratioOpesCorto);
		
		return beanRatioCorto;
		
		
	}

}
