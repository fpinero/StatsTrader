package com.fpe.statsTrader.jpa;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.hibernate.Session;

import com.fpe.statsTrader.GlobalVars;
import com.fpe.statsTrader.entity.TradersOpes;

//@Component
//@ManagedBean
//@SessionScoped
public class UpdateDatosOpe {
	
	//retorna true si todo va bien
	public boolean updateTraderOpe(TradersOpes opeModificada) {
		
		boolean actualizacionExitosa = false;
		
		//este truco de sumar 5 horas a la fecha es necesario pq el <f:convertDateTime pattern="dd-MM-yyyy" />
		//falla numerosas veces cuando la hora es 00:00:00 y formatea la fecha al día previo
		
		Calendar c = GregorianCalendar.getInstance();
		c.setTime(opeModificada.getFechaTrade());
		c.set(Calendar.HOUR_OF_DAY, 5);
		Date opeTime = c.getTime();
		opeModificada.setFechaTrade(opeTime);
		
		System.out.println("operación a actualizar en la BD: " + opeModificada.toString());
		
		// Creemos la sesión
		Session session = GlobalVars.factory.getCurrentSession();
		
		try {
			//comenzamos la transacción
			session.beginTransaction();
			//actualizamos el objeto
			session.update(opeModificada);
			//hacemos el commit
			session.getTransaction().commit();
			
			actualizacionExitosa = true;
		
		}catch (Exception e) {
			System.out.println("Excepción actualizando operación en la BD updateTraderOpe \n" + e.getMessage());
		}finally{
	        session.close();
	    }
	
		return actualizacionExitosa;
		
	}
	
}
