package com.fpe.statsTrader.jpa;

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
		
		System.out.println("Trader a ingresar en la BD: " + operacionTrader.toString());
		
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
