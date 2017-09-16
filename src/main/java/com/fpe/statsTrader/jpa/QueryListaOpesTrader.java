package com.fpe.statsTrader.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.fpe.statsTrader.GlobalVars;
import com.fpe.statsTrader.entity.Trader;
import com.fpe.statsTrader.entity.TradersOpes;
import com.fpe.statsTrader.utils.ConvertToSqlDateFormatWithQuotes;

public class QueryListaOpesTrader {
	
	public List<TradersOpes> getTradersOpes(Trader currentTrader, Date fechaIni, Date fechaFin) {
		
		List<TradersOpes> thisTradersOpes = new ArrayList<>();
		
		ConvertToSqlDateFormatWithQuotes convert = new ConvertToSqlDateFormatWithQuotes();
		
		int idTrader = currentTrader.getId();
		System.out.println("\nId del trader trader logueado: " + idTrader);
		
		// Creemos la sesión
		Session session = GlobalVars.factory.getCurrentSession();
		
		try {
			//comencemos la transaccion
			session.beginTransaction();
			
			//obtengamos la lista de operaciones del trader
			String query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(fechaIni) +
					" AND t.fechaTrade <=" + convert.converDate(fechaFin);
			System.out.println("query=" + query);
			thisTradersOpes = session.createQuery(query).getResultList();
			
			//hagamos el commit
			session.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("Excepción en getTradersOpes \n" + e.getMessage());
			return null;
		}
		
		return thisTradersOpes;
		
	}

}
