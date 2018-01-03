package com.fpe.statsTrader.jpa;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.fpe.statsTrader.utils.ConvertToSqlDateFormatWithQuotes;

public class QueryEstadisticasMensuales {
	
	//para poder ejecutarlo manualmente
	public static void main(String[] args) {
		
		Calendar fechaNow = GregorianCalendar.getInstance();
		int mesActual = fechaNow.get(Calendar.MONTH);
		int anoActual = fechaNow.get(Calendar.YEAR);
		int primerMesBusqueda = 0; //enero
		int primerAnoBusqueda = 2017; 
		int finMesBusqueda = mesActual;
		int finAnoBusqueda = anoActual;
		
		ConvertToSqlDateFormatWithQuotes convert = new ConvertToSqlDateFormatWithQuotes();
		
		
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
			
			System.out.println("***************************************************");
			System.out.println("FechaIniBusqueda=" + convert.converDate(fechaIniBus));
			System.out.println("FechaFinBusqueda=" + convert.converDate(fechaFinBus));
			System.out.println("***************************************************");
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
		
		System.out.println("----- busqueda extra -----");
		System.out.println("***************************************************");
		System.out.println("FechaIniBusqueda=" + convert.converDate(fechaIni));
		System.out.println("FechaFinBusqueda=" + convert.converDate(fechaFin));
		System.out.println("***************************************************");
		
		System.out.println("\n...Fin ejecución");
		
	}
	

}
