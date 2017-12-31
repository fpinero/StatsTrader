package com.fpe.statsTrader.jpa;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;

import org.hibernate.Session;

import com.fpe.statsTrader.GlobalVars;
import com.fpe.statsTrader.entity.Trader;
import com.fpe.statsTrader.entity.TradersOpes;
import com.fpe.statsTrader.utils.ConvertToSqlDateFormatWithQuotes;
import com.fpe.statsTrader.utils.EstadoActualObjetivos;

public class QueryEstadoActualObjetivos {
	
	private Date fechaPrimeroDeMes;
	private Date fechaLunesEstaSemana;
	private Date fechaHoy;
	
	public EstadoActualObjetivos obtenEstadoActualObjetivos() {
		
		ConvertToSqlDateFormatWithQuotes convert = new ConvertToSqlDateFormatWithQuotes();
		
		Calendar fechaNow = GregorianCalendar.getInstance();
		//primero obtengamos la fecha primero de mes
		fechaNow.set(Calendar.DAY_OF_MONTH, 1);
		fechaPrimeroDeMes = fechaNow.getTime();
		
		//calculemos la fecha de hoy + 1
		fechaNow = GregorianCalendar.getInstance();
		fechaNow.add(Calendar.DAY_OF_MONTH, 1); //sumamos un día para la query y el bug de primefaces
		fechaHoy = fechaNow.getTime();
		
		//Obtengamos el Lunes de esta semana
		Locale locale = new Locale("Europe/Madrid");
		fechaNow = GregorianCalendar.getInstance(locale);
		Locale.setDefault(locale);
		fechaNow.setFirstDayOfWeek(2); //monday
//		System.out.println(Locale.getDefault() + ": " + fechaNow.getFirstDayOfWeek());
//		System.out.println("Calendar.DAY_OF_WEEK" + ": " + Calendar.DAY_OF_WEEK);
//		System.out.println("fechaNow.get(Calendar.DAY_OF_WEEK)" + ":" +fechaNow.get(Calendar.DAY_OF_WEEK)) ;
		while (fechaNow.get(Calendar.DAY_OF_WEEK) != fechaNow.getFirstDayOfWeek()) { 
			fechaNow.add(Calendar.DATE, -1); // Substract 1 day until first day of week.
//			System.out.println("restando 1 dia a la semana..." + fechaNow.getTime());
		}
		
		/*while (fechaNow.get(Calendar.DAY_OF_WEEK) > fechaNow.getFirstDayOfWeek()) { 
			fechaNow.add(Calendar.DATE, -1); // Substract 1 day until first day of week.
			System.out.println("restando 1 dia a la semana..." + fechaNow.getTime());
		}*/
		 
		
		fechaLunesEstaSemana = fechaNow.getTime();
		System.out.println("++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Este lunes fue el " + convert.converDate(fechaLunesEstaSemana));
		System.out.println("++++++++++++++++++++++++++++++++++++++++");
		
		EstadoActualObjetivos beanEstadoActualObjetivos = new EstadoActualObjetivos();
		
		List<TradersOpes> thisTradersOpes = new ArrayList<>();
		
		Trader thisTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
		int idTrader = thisTrader.getId();
		System.out.println("...id del trader logueado en obtenEstadoActualObjetivos: " + idTrader);
		
		
		
		// Creemos la sesión
		Session session = GlobalVars.factory.getCurrentSession();
		
		int opesBuenas = 0;
		int totalOpesMes = 0;
		double netoDelMes = 0d;
		double netoDeLaSemana = 0d;
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		
		try {
			//comencemos la transaccion
			session.beginTransaction();
			
			//Obtengamos las operaciones buenas del mes
			String query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(fechaPrimeroDeMes) +
					" AND t.fechaTrade <" + convert.converDate(fechaHoy) + " AND t.resultadoTrade='Bueno'";
			System.out.println("query obtenRatio=" + query);
			thisTradersOpes = session.createQuery(query).getResultList();
			
			if (!thisTradersOpes.isEmpty()) {
				opesBuenas = thisTradersOpes.size();
			} else {
				opesBuenas = 0;
			}
			System.out.println("...Operaciones buenas del trader=" + opesBuenas);
			
			//Obtengamos todas las operaciones del mes
			query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(fechaPrimeroDeMes) +
					" AND t.fechaTrade <" + convert.converDate(fechaHoy);
			System.out.println("query obtenRatio=" + query);
			thisTradersOpes = session.createQuery(query).getResultList();
			
			if (!thisTradersOpes.isEmpty()) {
				totalOpesMes = thisTradersOpes.size();
			} else {
				totalOpesMes = 0;
			}
			System.out.println("...Total operaciones del mes de este trader=" + totalOpesMes);
			
			//obtengamos el neto del mes
			for (TradersOpes tr : thisTradersOpes) {
				netoDelMes += tr.getNetoOpe();
//				System.out.println("tr.getNetoOpe()=" + tr.getNetoOpe());
//				System.out.println("netoDelMes=" + netoDelMes);
			}
			netoDelMes = Double.parseDouble(df.format(netoDelMes).replace("," , "."));
			
			System.out.println("...Neto del mes del trader=" + netoDelMes);
			
			//Obtengamos las operaciones de la semana para el neto semanal
			query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(fechaLunesEstaSemana) +
					" AND t.fechaTrade <" + convert.converDate(fechaHoy);
			System.out.println("query obtenRatio=" + query);
			thisTradersOpes = session.createQuery(query).getResultList();
			
			//obtengamos el neto de la semana
			for (TradersOpes tr : thisTradersOpes) {
				netoDeLaSemana += tr.getNetoOpe();
//				System.out.println("tr.getNetoOpe()=" + tr.getNetoOpe());
//				System.out.println("netoDeLaSemana=" + netoDeLaSemana);
			}
			netoDeLaSemana = Double.parseDouble(df.format(netoDeLaSemana).replace("," , "."));
			
			System.out.println("...Neto de la semana del trader=" + netoDeLaSemana);
			
			//hagamos el commit
			session.getTransaction().commit();
			
			
		} catch (Exception e) {
			System.out.println("Excepción en obtenEstadoActualObjetivos \n" + e);
			return null;
		}
		
		
		
		//calculemos el ratio del mes
		double ratio = 0d;
		
		try {
			ratio = Double.parseDouble((df.format((opesBuenas * 100)/totalOpesMes).replace("," , ".")));
		} catch (Exception e) {
			// por si totalOpes = 0
			System.out.println("---> excepcion calculando el ratio del mes " + e);
			ratio = 0;
		}
		System.out.println("...ratio del trader del mes=" + ratio);
		
		beanEstadoActualObjetivos.setRatioMes(ratio);
		beanEstadoActualObjetivos.setNetoMes(netoDelMes);
		beanEstadoActualObjetivos.setNetoSemana(netoDeLaSemana);
		
		return beanEstadoActualObjetivos;
		
	}

}
