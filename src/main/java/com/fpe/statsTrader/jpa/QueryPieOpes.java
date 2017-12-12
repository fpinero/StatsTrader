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
import com.fpe.statsTrader.utils.PieOpes;

public class QueryPieOpes {
	
	int opesBuenas = 0;
	int opesMalas = 0;
	int opesBe = 0;
	
//	@ManagedProperty(value="#{pieOPes}")
//	PieOpes beanPie;
	
	public PieOpes buscaOPesBMB(Date desdeFecha, Date hastaFecha) {
		
		PieOpes beanPie = new PieOpes();
		
		Trader thisTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
		int idTrader = thisTrader.getId();
		System.out.println("...id del trader logueado en QueryPieOpes: " + idTrader);
		
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
			
			//Obtengamos las operaciones buenas
			String query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.resultadoTrade='Bueno'";
			System.out.println("query=" + query);
			thisTradersOpes = session.createQuery(query).getResultList();
			
			//hagamos el commit
			//session.getTransaction().commit();
			
			if (!thisTradersOpes.isEmpty()) {
				opesBuenas = thisTradersOpes.size();
			} else {
				opesBuenas = 0;
			}
			System.out.println("...Operaciones buenas del trader=" + opesBuenas);
			
			//Obtengamos las operaciones malas
			
			//comencemos la transaccion
			//session.beginTransaction();
			
			//Obtengamos las operaciones malas
			query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.resultadoTrade='Stop'";
			System.out.println("query=" + query);
			thisTradersOpes = session.createQuery(query).getResultList();
			
			//hagamos el commit
			//session.getTransaction().commit();
			
			if (!thisTradersOpes.isEmpty()) {
				opesMalas = thisTradersOpes.size();
			} else {
				opesMalas = 0;
			}
			System.out.println("...Operaciones malas del trader=" + opesMalas);
			
			//Obtengamos las operaciones breakeven
			query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.resultadoTrade='BreakEven'";
			System.out.println("query=" + query);
			thisTradersOpes = session.createQuery(query).getResultList();
			
			//hagamos el commit
			session.getTransaction().commit();
			
			if (!thisTradersOpes.isEmpty()) {
				opesBe = thisTradersOpes.size();
			} else {
				opesBe = 0;
			}
			System.out.println("...Operaciones BreakEven del trader=" + opesMalas);
			
		} catch (Exception e) {
			System.out.println("Excepción en buscaOPesBMB \n" + e);
			return null;
		}
		
		beanPie.setOpesBuenas(opesBuenas);
		beanPie.setOpesMalas(opesMalas);
		beanPie.setOpesBe(opesBe);
		
		return beanPie;
	}

}
