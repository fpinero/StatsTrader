package com.fpe.statsTrader.jpa;

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
import com.fpe.statsTrader.utils.Patron1m;

public class QueryEstadisticas1m {
	
	public Patron1m obtenDatosDeStse1m(String patron1, String side, Date desdeFecha, Date hastaFecha){
		
		Patron1m patron1bean = new Patron1m();
		
		int numeroStops = 0;
		int numeroBuenas = 0;
		int numeroBe = 0;
		int vecesNegociado = 0;
		String patronNegociado = patron1;
		
		Trader thisTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
		int idTrader = thisTrader.getId();
		System.out.println("...id del trader logueado en QueryEstadisticas1m: " + idTrader);
		
		ConvertToSqlDateFormatWithQuotes convert = new ConvertToSqlDateFormatWithQuotes();
		
		List<TradersOpes> thisTradersOpes = new ArrayList<>();
		
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
			
			//Obtengamos las operaciones en 1m 
			String query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.patronTrade1m='" + patron1 + "'" +
					" AND t.sideTrade=" + "'" + side + "'";
			System.out.println("query=" + query);
			thisTradersOpes = session.createQuery(query).getResultList();
			
			for (TradersOpes to : thisTradersOpes  ) {
				System.out.println("thitTo=" + to.toString());
				vecesNegociado++;
				if (to.getResultadoTrade().equals("Bueno")) {
					numeroBuenas++;
				} else if (to.getResultadoTrade().equals("Stop")) {
					numeroStops++;
				} else {
					numeroBe++;
				}
			}
			
			//hagamos el commit
			session.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("Excepción en obtenDatosDeStse1m \n" + e);
			return null;
		}
		
		System.out.println("patronNegociado=" + patronNegociado);
		System.out.println("numeroBuenas=" + numeroBuenas);
		System.out.println("numeroStops=" + numeroStops);
		System.out.println("numeroBe=" + numeroBe);
		System.out.println("vecesNegociado=" + vecesNegociado);
		
		patron1bean.setNumeroBe(numeroBe);
		patron1bean.setNumeroBuenas(numeroBuenas);
		patron1bean.setNumeroStops(numeroStops);
		patron1bean.setPatron(patronNegociado);
		patron1bean.setVecesNegociado(vecesNegociado);
		
		return patron1bean;
		
	}

}
