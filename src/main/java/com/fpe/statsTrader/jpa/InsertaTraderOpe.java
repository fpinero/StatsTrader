package com.fpe.statsTrader.jpa;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.fpe.statsTrader.GlobalVars;
import com.fpe.statsTrader.entity.TradersOpes;

@Component
@ManagedBean
@SessionScoped
public class InsertaTraderOpe {
	
	public boolean saveOpe(TradersOpes operacionTrader) {

		boolean exito = false;
		
		//este truco de sumar 5 horas a la fecha es necesario pq el <f:convertDateTime pattern="dd-MM-yyyy" />
		//falla numerosas veces cuando la hora es 00:00:00 y formatea la fecha al día previo
		
		Calendar c = GregorianCalendar.getInstance();
		c.setTime(operacionTrader.getFechaTrade());
		c.set(Calendar.HOUR_OF_DAY, 5);
		Date opeTime = c.getTime();
		operacionTrader.setFechaTrade(opeTime);
		
		System.out.println("Trade a ingresar en la BD: " + operacionTrader.toString());
		
		// Creemos la sesión
		Session session = GlobalVars.factory.getCurrentSession();
		
		try {
			//comenzamos la transacción
			session.beginTransaction();
			//salvamos el objeto
			session.save(operacionTrader);
			//hacemos el commit
			session.getTransaction().commit();
			exito = true;
		
		}catch (Exception e) {
			System.out.println("Excepción salvando trader a la BD \n" + e.getMessage());
		}
	
		return exito;
	}
	
}
