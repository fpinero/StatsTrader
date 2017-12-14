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
import com.fpe.statsTrader.utils.TraderRatio;

public class QueryTraderRatio {
	
	private double ratio = 0.0;
	private double ratioSinStopsEvitables = 0.0;
	private double ratioTodoAcordePlan = 0.0;
	private int opesBuenas = 0;
	private int opesStop = 0;
	private int opesBe = 0;
	private int opesStopEvitable = 0;
	private int opesStopNoEvitable = 0;
	private int opesTodoAcordePlan = 0;
	private int opesNoTodoAcordePlan = 0;
	private int opesBuenasTodoAcordePlan = 0;
	private int opesBuenasNoTodoAcordePlan = 0;
	
	public TraderRatio obtenRatio(Date desdeFecha, Date hastaFecha) {
		
		TraderRatio beanTraderRatio = new TraderRatio();
		
		Trader thisTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
		int idTrader = thisTrader.getId();
		System.out.println("...id del trader logueado en QueryTraderRatio: " + idTrader);
		
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
			
			if (!thisTradersOpes.isEmpty()) {
				opesBuenas = thisTradersOpes.size();
			} else {
				opesBuenas = 0;
			}
			System.out.println("...Operaciones buenas del trader=" + opesBuenas);
			
			//Obtengamos las operaciones malas
			query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.resultadoTrade='Stop'";
			System.out.println("query=" + query);
			thisTradersOpes = session.createQuery(query).getResultList();
			
			if (!thisTradersOpes.isEmpty()) {
				opesStop = thisTradersOpes.size();
			} else {
				opesStop = 0;
			}
			System.out.println("...Operaciones malas del trader=" + opesStop);
			
			//Obtengamos las operaciones breakeven
			query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.resultadoTrade='BreakEven'";
			System.out.println("query=" + query);
			thisTradersOpes = session.createQuery(query).getResultList();
			
			if (!thisTradersOpes.isEmpty()) {
				opesBe = thisTradersOpes.size();
			} else {
				opesBe = 0;
			}
			System.out.println("...Operaciones BreakEven del trader=" + opesBe);
			
			//Obtengamos las operaciones stop evitables
			query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.resultadoTrade='Stop'" +
					" AND t.stopEvitable='SI'";
			System.out.println("query=" + query);
			thisTradersOpes = session.createQuery(query).getResultList();
			
			if (!thisTradersOpes.isEmpty()) {
				opesStopEvitable = thisTradersOpes.size();
			} else {
				opesStopEvitable = 0;
			}
			System.out.println("...Operaciones opesStopEvitable del trader=" + opesStopEvitable);
			
			//Obtengamos las operaciones stop no evitables
			query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.resultadoTrade='Stop'" +
					" AND t.stopEvitable='NO'";
			System.out.println("query=" + query);
			thisTradersOpes = session.createQuery(query).getResultList();
			
			if (!thisTradersOpes.isEmpty()) {
				opesStopNoEvitable = thisTradersOpes.size();
			} else {
				opesStopNoEvitable = 0;
			}
			System.out.println("...Operaciones opesStopNoEvitable del trader=" + opesStopNoEvitable);
			
			//Obtengamos las operaciones todo acorde el plan
			query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.todo100plan='SI'";
					
			System.out.println("query=" + query);
			thisTradersOpes = session.createQuery(query).getResultList();
			
			if (!thisTradersOpes.isEmpty()) {
				opesTodoAcordePlan = thisTradersOpes.size();
			} else {
				opesTodoAcordePlan = 0;
			}
			System.out.println("...Operaciones todo acorde el plan del trader=" + opesTodoAcordePlan);
			
			//Obtengamos las operaciones todo no acorde el plan
			query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.todo100plan='NO'";
					
			System.out.println("query=" + query);
			thisTradersOpes = session.createQuery(query).getResultList();
			
			if (!thisTradersOpes.isEmpty()) {
				opesNoTodoAcordePlan = thisTradersOpes.size();
			} else {
				opesNoTodoAcordePlan = 0;
			}
			System.out.println("...Operaciones todo no acorde el plan del trader=" + opesNoTodoAcordePlan);
			
			//Obtengamos las operaciones buenas todo acorde al plan
			query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.resultadoTrade='Bueno'" +
					" AND t.todo100plan='SI'";
			System.out.println("query=" + query);
			thisTradersOpes = session.createQuery(query).getResultList();
			
			if (!thisTradersOpes.isEmpty()) {
				opesBuenasTodoAcordePlan = thisTradersOpes.size();
			} else {
				opesBuenasTodoAcordePlan = 0;
			}
			System.out.println("...Operaciones buenas todo acorde al plan=" + opesBuenasTodoAcordePlan);
			
			//Obtengamos las operaciones buenas no todo acorde al plan
			query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.resultadoTrade='Bueno'" +
					" AND t.todo100plan='NO'";
			System.out.println("query=" + query);
			thisTradersOpes = session.createQuery(query).getResultList();
			
			if (!thisTradersOpes.isEmpty()) {
				opesBuenasNoTodoAcordePlan = thisTradersOpes.size();
			} else {
				opesBuenasNoTodoAcordePlan = 0;
			}
			System.out.println("...Operaciones buenas no todo acorde al plan=" + opesBuenasNoTodoAcordePlan);
			
			//hagamos el commit
			session.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("Excepción en obtenRatio \n" + e);
			return null;
		}
		
		DecimalFormat df = new DecimalFormat("#,00");
		
		
		//calculemos el ratio
		int totalOpes = opesBuenas + opesStop; //se ignoran los breakeven
		try {
			ratio = Double.parseDouble((df.format((opesBuenas * 100)/totalOpes)));
		} catch (Exception e) {
			// por si totalOpes = 0
			System.out.println("---> excepcion calculando el ratio " + e);
			ratio = 0;
		}
		System.out.println("...ratio del trader=" + ratio);
		
		//calculemos el ratio sin stops evitables
		totalOpes = opesBuenas + opesStopEvitable;
		try {
			ratioSinStopsEvitables = Double.parseDouble((df.format((opesBuenas * 100)/totalOpes)));
		} catch (Exception e) {
			// por si totalOpes = 0
			System.out.println("---> excepcion calculando el ratio sin stops evitables " + e);
			ratioSinStopsEvitables = 0;
		}
		System.out.println("...ratio sin stops evitables del trader=" + ratioSinStopsEvitables);
		
		//calculemos el ratio con todo acorde el plan
		int tmpOpesStopNoEvitable = opesStopNoEvitable; //se ignoran los breakeven
		if (opesStopNoEvitable == 0) {
			tmpOpesStopNoEvitable = 1;
		}
		try {
			ratioTodoAcordePlan = Double.parseDouble((df.format((tmpOpesStopNoEvitable * 100) / opesTodoAcordePlan)));
			ratioTodoAcordePlan = 100 - ratioTodoAcordePlan;
		} catch (Exception e) {
			// por si totalOpes = 0
			System.out.println("---> excepcion calculando el ratio con todo acorde el plan " + e);
			ratioTodoAcordePlan = 0;
		}
		System.out.println("...ratio con todo acorde el plan del trader=" + ratioTodoAcordePlan);
		
		beanTraderRatio.setRatio(ratio);
		beanTraderRatio.setRatioSinStopsEvitables(ratioSinStopsEvitables);
		beanTraderRatio.setRatioTodoAcordePlan(ratioTodoAcordePlan);
		beanTraderRatio.setOpesBuenas(opesBuenas);
		beanTraderRatio.setOpesStop(opesStop);
		beanTraderRatio.setOpesBe(opesBe);
		beanTraderRatio.setOpesStopEvitable(opesStopEvitable);
		beanTraderRatio.setOpesStopNoEvitable(opesStopNoEvitable);
		beanTraderRatio.setOpesTodoAcordePlan(opesTodoAcordePlan);
		beanTraderRatio.setOpesNoTodoAcordePlan(opesNoTodoAcordePlan);
		beanTraderRatio.setOpesBuenasTodoAcordePlan(opesBuenasTodoAcordePlan);
		beanTraderRatio.setOpesBuenasNoTodoAcordePlan(opesBuenasNoTodoAcordePlan);
		
		return beanTraderRatio;
	}
		
		

}
