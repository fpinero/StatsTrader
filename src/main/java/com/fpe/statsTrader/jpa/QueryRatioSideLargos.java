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
import com.fpe.statsTrader.utils.RatioSideLargo;

public class QueryRatioSideLargos {
	
	int numeroOpesLargo = 0;
	int numeroBuenasLargo = 0;
	double ratioOpesLargo = 0d;
	
	public RatioSideLargo obtenRatioSideOpesLargo(Date desdeFecha, Date hastaFecha) {
		
		RatioSideLargo beanRatioLargo = new RatioSideLargo();
		
		List<TradersOpes> thisTradersOpes = new ArrayList<>();
		
		Trader thisTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
		int idTrader = thisTrader.getId();
		System.out.println("...id del trader logueado en QueryRatioSideLargos: " + idTrader);
		
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
			
			//Obtengamos las operaciones buenas en largo
			String query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.resultadoTrade='Bueno' AND t.sideTrade='Largo'";
			System.out.println("query obtenRatio=" + query);
			
			thisTradersOpes = session.createQuery(query).getResultList();
			
			if (!thisTradersOpes.isEmpty()) {
				numeroBuenasLargo = thisTradersOpes.size();
			} else {
				numeroBuenasLargo = 0;
			}
			System.out.println("...numeroBuenasLargo del trader=" + numeroBuenasLargo);
			
			//Obtengamos todas las operaciones realizadas
			query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.sideTrade='Largo'";
			System.out.println("query obtenRatio=" + query);
			
			thisTradersOpes = session.createQuery(query).getResultList();
			
			if (!thisTradersOpes.isEmpty()) {
				numeroOpesLargo = thisTradersOpes.size();
			} else {
				numeroOpesLargo = 0;
			}
			System.out.println("...numeroOpesLargo del trader=" + numeroOpesLargo);
			
			//hagamos el commit
			session.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("Excepción en obtenRatioSideOpesLargo \n" + e);
			return null;
		}
		
		//calculemos el ratio
		//(numeroBuenasLargo * 100) / numeroOpesLargo
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		
		if (numeroOpesLargo == 0) {
			ratioOpesLargo = 0d;
		} else {
			ratioOpesLargo = Double.parseDouble((df.format((numeroBuenasLargo * 100)/numeroOpesLargo).replace("," , ".")));
		}
		
		System.out.println("ratioOpesLargo del trader=" + ratioOpesLargo);
		
		beanRatioLargo.setNumeroBuenasLargo(numeroBuenasLargo);
		beanRatioLargo.setNumeroOpesLargo(numeroOpesLargo);
		beanRatioLargo.setRatioOpesLargo(ratioOpesLargo);
		
		return beanRatioLargo;
		
		
	}

}
