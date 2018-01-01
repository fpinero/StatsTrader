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
import com.fpe.statsTrader.utils.CombinacionMejorPatron15m;
import com.fpe.statsTrader.utils.ConvertToSqlDateFormatWithQuotes;

public class QueryCombinacionMejorPatron15m {
	
	String patron15mLargo = "";
	String patron1mLargo = "";
	int numeroVecesNegociadoLargo = 0;
	int numeroOpesBuenasLargo = 0;
	String patron15mCorto = "";
	String patron1mCorto = "";
	int numeroVecesNegociadoCorto = 0;
	int numeroOpesBuenasCorto = 0;
	
	public CombinacionMejorPatron15m obtenMejorCombinacion15m(Date desdeFecha, Date hastaFecha) {
		
		CombinacionMejorPatron15m beanCombinacionMejorPatron15m = new CombinacionMejorPatron15m();
		
		List<TradersOpes> thisTradersOpes = new ArrayList<>();
		
		Trader thisTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
		int idTrader = thisTrader.getId();
		System.out.println("...id del trader logueado en QueryCombinacionMejorPatron15m: " + idTrader);
		
		ConvertToSqlDateFormatWithQuotes convert = new ConvertToSqlDateFormatWithQuotes();
		
		//para no usar el = en fechaFinal le sumamos un dia y solo usamos el <
		
		Calendar c = GregorianCalendar.getInstance();
		c.setTime(hastaFecha);
		c.add(Calendar.DAY_OF_MONTH, 1); //sumamos un día
		hastaFecha = c.getTime();
		
		// Creemos la sesión
		Session session = GlobalVars.factory.getCurrentSession();
		
		String side = "Largo";
		
		try {
			
			//comencemos la transaccion
			session.beginTransaction();
			
			//Obtengamos las operaciones buenas realizadas en el rango de fechas en largo
			String query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.resultadoTrade='Bueno' AND t.sideTrade='" + 
					side + "'";
			System.out.println("query patrones en " + side + "=" + query);
			
			thisTradersOpes = session.createQuery(query).getResultList();
			
			int numeroVecesNego = 0;
			for (TradersOpes tr : thisTradersOpes){
				String patron15mUtilizado = tr.getPatronTrade15m();
				int ocurrencias = vecesUsadoEstePatron15(thisTradersOpes, patron15mUtilizado);
				if (ocurrencias > numeroVecesNego) {
					patron15mLargo = patron15mUtilizado;
					numeroVecesNego = ocurrencias;
				}
			}
			
			System.out.println("###### Mejor patron usado en 15m en " + side + " = " + patron15mLargo);
			
			// hagamos ahora la query de que patrón se usó mas veces con este patrón
			//hay que sacar dos cosas, el numero de veces negociado y las veces que fue buena la ope
			
			query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.sideTrade='" + 
					side + "' AND t.patronTrade15m='" + patron15mLargo + "'";
			System.out.println("query operaciones en " + side + " con patron" + patron15mLargo + "=" + query);
			
			thisTradersOpes = session.createQuery(query).getResultList();
			
			//con esto ya tengo el número de veces utilizado en largo
			if (!thisTradersOpes.isEmpty()) {
				numeroVecesNegociadoLargo = thisTradersOpes.size();
			}
			
			System.out.println("###### Numero de veces usado el patron " + patron15mLargo + " en 15m en " + side + " = " + numeroVecesNegociadoLargo);
			
			int numeroOpesBuenasLargo1m = 0;
			for (TradersOpes tr : thisTradersOpes){
				String patron1mUtilizado = tr.getPatronTrade1m();
				if (tr.getResultadoTrade().equalsIgnoreCase("Bueno")){
					int ocurrencias = vecesUsadoEstePatron1(thisTradersOpes, patron1mUtilizado);
					if (ocurrencias > numeroOpesBuenasLargo1m) {
						patron1mLargo = patron1mUtilizado;
						numeroOpesBuenasLargo = ocurrencias;
						numeroOpesBuenasLargo1m = ocurrencias;
					}
				}
			}
			
			System.out.println("###### Mejor patron usado en 1m en " + side + " = " + patron1mLargo + "conjuntamente con el patron en 15m " + patron15mLargo);
			
			//#######################
			//ahora lo mismo pero para cortos
			side = "Corto";
			//#######################
			
			//Obtengamos las operaciones buenas en corto
			query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.resultadoTrade='Bueno' AND t.sideTrade='" + 
					side + "'";
			System.out.println("query patrones en " + side + "=" + query);
			
			thisTradersOpes = session.createQuery(query).getResultList();
			
			numeroVecesNego = 0;
			for (TradersOpes tr : thisTradersOpes){
				String patron15mUtilizado = tr.getPatronTrade15m();
				int ocurrencias = vecesUsadoEstePatron15(thisTradersOpes, patron15mUtilizado);
				if (ocurrencias > numeroVecesNego) {
					patron15mCorto = patron15mUtilizado;
					numeroVecesNego = ocurrencias;
				}
			}
			
			System.out.println("###### Mejor patron usado en 15m en " + side + " = " + patron15mCorto);
			
			// hagamos ahora la query de que patrón se usó mas veces con este patrón
			//hay que sacar dos cosas, el numero de veces negociado y las veces que fue buena la ope
			
			query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha) + " AND t.sideTrade='" + 
					side + "' AND t.patronTrade15m='" + patron15mCorto + "'";
			System.out.println("query operaciones en " + side + " con patron" + patron15mCorto + "=" + query);
			
			thisTradersOpes = session.createQuery(query).getResultList();
			
			//con esto ya tengo el número de veces utilizado en corto
			if (!thisTradersOpes.isEmpty()) {
				numeroVecesNegociadoCorto = thisTradersOpes.size();
			}
			
			System.out.println("###### Numero de veces usado el patron " + patron15mCorto + " en 15m en " + side + " = " + numeroVecesNegociadoCorto);
			
			int numeroOpesBuenasCorto1m = 0;
			for (TradersOpes tr : thisTradersOpes){
				String patron1mUtilizado = tr.getPatronTrade1m();
				if (tr.getResultadoTrade().equalsIgnoreCase("Bueno")){
//					System.out.println("---------||||||---> jugada en corto=" + tr.getSymbolTrade() + " resultado=" + tr.getResultadoTrade());
					int ocurrencias = vecesUsadoEstePatron1(thisTradersOpes, patron1mUtilizado);
					if (ocurrencias > numeroOpesBuenasCorto1m) {
						patron1mCorto = patron1mUtilizado;
						numeroOpesBuenasCorto = ocurrencias;
						numeroOpesBuenasCorto1m = ocurrencias;
					}
				}
			}
			
			System.out.println("###### Mejor patron usado en 1m en " + side + " = " + patron1mCorto + "conjuntamente con el patron en 15m " + patron15mCorto);
			
			//hagamos el commit
			session.getTransaction().commit();
			
			
		} catch (Exception e) {
			System.out.println("Excepción en obtenMejorCombinacion15m \n" + e);
			return null;
		}finally{
	        session.close();
	    }
		
		beanCombinacionMejorPatron15m.setPatron15mLargo(patron15mLargo);
		beanCombinacionMejorPatron15m.setPatron1mLargo(patron1mLargo);
		beanCombinacionMejorPatron15m.setNumeroVecesNegociadoLargo(numeroVecesNegociadoLargo);
		beanCombinacionMejorPatron15m.setNumeroOpesBuenasLargo(numeroOpesBuenasLargo);
		beanCombinacionMejorPatron15m.setPatron15mCorto(patron15mCorto);
		beanCombinacionMejorPatron15m.setPatron1mCorto(patron1mCorto);
		beanCombinacionMejorPatron15m.setNumeroVecesNegociadoCorto(numeroVecesNegociadoCorto);
		beanCombinacionMejorPatron15m.setNumeroOpesBuenasCorto(numeroOpesBuenasCorto);
		
		System.out.println("...beanCombinacionMejorPatron15m=" + beanCombinacionMejorPatron15m);
		
		return beanCombinacionMejorPatron15m;
		
	}
	
	private int vecesUsadoEstePatron15(List<TradersOpes> listadoOpes, String patron) {
		
		int n = 0;
		for (TradersOpes to : listadoOpes) {
//			System.out.println("----------------> comparando 15m " + patron + " con " + to.getPatronTrade15m() + " del symbol=" + to.getSymbolTrade());
			if (patron.equals(to.getPatronTrade15m())){
				n++;
			}
		}
		
//		System.out.println("Patron bueno 15m " + patron + " utilizado " + n + " veces");
		return n;
	}
	
private int vecesUsadoEstePatron1(List<TradersOpes> listadoOpes, String patron) {
		
		int n = 0;
		for (TradersOpes to : listadoOpes) {
//			System.out.println("----------------> comparando " + patron + " con " + to.getPatronTrade1m() + " del symbol=" + to.getSymbolTrade());
			if (patron.equals(to.getPatronTrade1m()) && "Bueno".equals(to.getResultadoTrade())){
//				System.out.println("----------------> comparando " + patron + " con " + to.getPatronTrade1m() + " del symbol=" + to.getSymbolTrade());
				n++;
			}
		}
		
//		System.out.println("Patron bueno 1m " + patron + " utilizado " + n + " veces");
		return n;
		}

}
