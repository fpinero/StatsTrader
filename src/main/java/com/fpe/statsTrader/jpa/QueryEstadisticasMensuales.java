package com.fpe.statsTrader.jpa;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import com.fpe.statsTrader.utils.EstadisticasMensuales;

public class QueryEstadisticasMensuales {
	
	
	public List<EstadisticasMensuales> obtenResultadosEstadisticaMensuales() {
		
		List<EstadisticasMensuales> listEstadisticasMensuales = new ArrayList<>();
		
		Trader thisTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
		int idTrader = thisTrader.getId();
		System.out.println("...id del trader logueado en QueryEstadisticasMensuales: " + idTrader);
		
		Calendar fechaNow = GregorianCalendar.getInstance();
		int mesActual = fechaNow.get(Calendar.MONTH);
		int anoActual = fechaNow.get(Calendar.YEAR);
		int primerMesBusqueda = 0; //enero
		int primerAnoBusqueda = 2017; 
		int finMesBusqueda = mesActual;
		int finAnoBusqueda = anoActual;
		
//		ConvertToSqlDateFormatWithQuotes convert = new ConvertToSqlDateFormatWithQuotes();
		
		while((finMesBusqueda <= primerMesBusqueda) && (finAnoBusqueda <= anoActual)) {
			
			Calendar hastaFecha = GregorianCalendar.getInstance();
			if (finMesBusqueda == 11) { //estamos en diciembre, hastaFecha debe ser 1 de Enero año siguiente
				finMesBusqueda = 0;
				hastaFecha.set(Calendar.DAY_OF_MONTH, 1);
				hastaFecha.set(Calendar.MONTH, finMesBusqueda);
				hastaFecha.set(Calendar.YEAR, primerAnoBusqueda + 1);
			} else {
				hastaFecha.set(Calendar.DAY_OF_MONTH, 1);
				hastaFecha.set(Calendar.MONTH, finMesBusqueda + 1);
				hastaFecha.set(Calendar.YEAR, primerAnoBusqueda);
			}
			Date fechaFinBus = hastaFecha.getTime();
			
			Calendar desdeFecha = GregorianCalendar.getInstance();
			desdeFecha.set(Calendar.DAY_OF_MONTH, 1);
			desdeFecha.set(Calendar.MONTH, primerMesBusqueda);
			desdeFecha.set(Calendar.YEAR, primerAnoBusqueda);
			Date fechaIniBus = desdeFecha.getTime();
			
//			System.out.println("***************************************************");
//			System.out.println("FechaIniBusqueda=" + convert.converDate(fechaIniBus));
//			System.out.println("FechaFinBusqueda=" + convert.converDate(fechaFinBus));
//			System.out.println("***************************************************");
			
			EstadisticasMensuales thisEstadisticasMensuales;
			thisEstadisticasMensuales = realizaLaQuery(idTrader, fechaIniBus, fechaFinBus);
			if (thisEstadisticasMensuales != null) {
				listEstadisticasMensuales.add(thisEstadisticasMensuales);
			}
			
			//aumentemos los contadores para la siguiente fecha
			primerMesBusqueda++;
			if (primerMesBusqueda == 12) {  //si vale 12 acabamos de procesar diciembre
				primerMesBusqueda = 0; //ponemos el mes de nuevo en Enero
				primerAnoBusqueda++; //añadimos un año mas
			}
			
			finMesBusqueda++;
			
		}
		
		//hagamos ahora una búsqueda extra por el mes en curso para q muestre lo que lleva
		
		Calendar hastaFecha = GregorianCalendar.getInstance();
		hastaFecha.set(Calendar.DAY_OF_MONTH, 1);
		hastaFecha.set(Calendar.YEAR, anoActual);
		hastaFecha.add(Calendar.MONTH, 1);  //eso es un add no un set
		Date fechaFin = hastaFecha.getTime();
		
		Calendar desdeFecha = GregorianCalendar.getInstance();
		desdeFecha.set(Calendar.DAY_OF_MONTH, 1);
		desdeFecha.set(Calendar.MONTH, mesActual);
		desdeFecha.set(Calendar.YEAR, anoActual);
		Date fechaIni = desdeFecha.getTime();
		
//		System.out.println("----- busqueda extra -----");
//		System.out.println("***************************************************");
//		System.out.println("FechaIniBusqueda=" + convert.converDate(fechaIni));
//		System.out.println("FechaFinBusqueda=" + convert.converDate(fechaFin));
//		System.out.println("***************************************************");
		
		EstadisticasMensuales thisEstadisticasMensuales;
		thisEstadisticasMensuales = realizaLaQuery(idTrader, fechaIni, fechaFin);
		if (thisEstadisticasMensuales != null) {
			listEstadisticasMensuales.add(thisEstadisticasMensuales);
//			System.out.println("thisEstadisticasMensuales=" + thisEstadisticasMensuales.toString());
		}
		
		return listEstadisticasMensuales;
		
	}
	
	private EstadisticasMensuales realizaLaQuery(int idTrader, Date desdeFecha, Date hastaFecha) {
		
		String mesAno;
		int sharesMovidas;
		int numeroTrades;
		int numeroBuenas;
		int numeroStops;
		int numeroBreakEven;
		double ratioMes;
		double brutoMes;
		double netoMes;
		
		SimpleDateFormat sdfMesAno = new SimpleDateFormat("MM/YYYY");
		EstadisticasMensuales tmpEstadisticasMensuales = new EstadisticasMensuales();
		
		mesAno = sdfMesAno.format(desdeFecha);
		System.out.println("Mes/Año para la cabecera=" + mesAno);
		
		ConvertToSqlDateFormatWithQuotes convert = new ConvertToSqlDateFormatWithQuotes();
		
		List<TradersOpes> thisTradersOpes = new ArrayList<>();
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		
		// Creemos la sesión
		Session session = GlobalVars.factory.getCurrentSession();
		
		try {
			
			//comencemos la transaccion
			session.beginTransaction();
			
			//Obtengamos todas las operaciones de este mes 
			String query = "from TradersOpes t WHERE t.traderId=" + idTrader + " AND t.fechaTrade >=" + convert.converDate(desdeFecha) +
					" AND t.fechaTrade <" + convert.converDate(hastaFecha);
			System.out.println("QueryEstadisticasMensuales query=" + query);
			thisTradersOpes = session.createQuery(query).getResultList();
			
			if (thisTradersOpes.isEmpty()) { //no negoció este mes
				tmpEstadisticasMensuales = null;
			} else { //negoció este mes
				
				//obtengamos cuantas operaciones hizo
				numeroTrades = thisTradersOpes.size();
				
				//obtengamos cuantas shares movió;
				sharesMovidas = 0;
				for (TradersOpes tr : thisTradersOpes ) {
					sharesMovidas += tr.getSharesTrade();
				}
				sharesMovidas = sharesMovidas * 2; // son el doble por la entrada y la salida
				
				//obtengamos el numero de operaciones buenas
				numeroBuenas = 0;
				for (TradersOpes tr : thisTradersOpes ) {
					if (tr.getResultadoTrade().equals("Bueno")) {
						numeroBuenas++;
					}
				}
				
				//obtengamos el numero de operaciones malas
				numeroStops = 0;
				for (TradersOpes tr : thisTradersOpes ) {
					if (tr.getResultadoTrade().equals("Stop")) {
						numeroStops++;
					}
				}
				
				//obtengamos el numero de operaciones breakeven
				numeroBreakEven = 0;
				for (TradersOpes tr : thisTradersOpes ) {
					if (tr.getResultadoTrade().equals("BreakEven")) {
						numeroBreakEven++;
					}
				}
				
				//obtengamos el bruto del mes
				double tmpBruto = 0d;
				for (TradersOpes tr : thisTradersOpes) {
					tmpBruto += tr.getBrutoOpe();
				}
				//System.out.println("tmpBruto=" + tmpBruto);
				try {
					brutoMes = Double.parseDouble(df.format(tmpBruto).replace(",", "")); //hay que quitar las , pq son miles en USA
				} catch (Exception e){
					System.out.println("excepcion parseando tmpBruto " + tmpBruto + " en realizaLaQuery\n" + e);
					brutoMes = 0d;
				}
				
				//obtengamos el neto del mes
				double tmpNeto = 0d;
				for (TradersOpes tr : thisTradersOpes) {
					tmpNeto += tr.getNetoOpe();
				}
				//System.out.println("tmpBruto=" + tmpBruto);
				try {
					netoMes = Double.parseDouble(df.format(tmpNeto).replace(",", "")); //hay que quitar las , pq son miles en USA
				} catch (Exception e){
					System.out.println("excepcion parseando tmpNeto " + tmpBruto + " en realizaLaQuery\n" + e);
					netoMes = 0d;
				}
				
				//calculemos el ratio
				///(numeroOpesBuenas * 100) / totalOpes sin las breakeven)
				
				try {
					ratioMes = Double.parseDouble(df.format(((numeroBuenas * 100) / (numeroTrades - numeroBreakEven))).replace("," , "."));
				} catch (Exception e) {
					System.out.println("excepcion calculando el ratio en realizaLaQuery \n" +e);
					ratioMes = 0;
				}
				
				//establezcamos las propiedades del objeto
				tmpEstadisticasMensuales.setMesAno(mesAno);
				tmpEstadisticasMensuales.setSharesMovidas(sharesMovidas);
				tmpEstadisticasMensuales.setNumeroTrades(numeroTrades);
				tmpEstadisticasMensuales.setNumeroBuenas(numeroBuenas);
				tmpEstadisticasMensuales.setNumeroStops(numeroStops);
				tmpEstadisticasMensuales.setNumeroBreakEven(numeroBreakEven);
				tmpEstadisticasMensuales.setRatioMes(ratioMes);
				tmpEstadisticasMensuales.setBrutoMes(brutoMes);
				tmpEstadisticasMensuales.setNetoMes(netoMes);
				
			} //fin del else de negoció
			
		} catch (Exception e) {
			System.out.println("Excepción en realizaLaQuery \n" + e);
			return null;
		} finally {
			session.close();
		}
		
		return tmpEstadisticasMensuales;
		
	}
	

}
