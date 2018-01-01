package com.fpe.statsTrader.jpa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.fpe.statsTrader.GlobalVars;
import com.fpe.statsTrader.entity.TradersOpes;

//@Component
@ManagedBean
@SessionScoped
public class DeleteTradersOpe {
	
	//retorna true si todo va bien
	public boolean deleteOpe(TradersOpes opeAeliminar) {
		
		boolean eliminacionExitosa = false;
		
		System.out.println("Operación a eliminar de la BD: " + opeAeliminar.toString());
		
		// Creemos la sesión
		Session session = GlobalVars.factory.getCurrentSession();
		
		try {
			//comenzamos la transacción
			session.beginTransaction();
			//eliminamos el objeto
			session.delete(opeAeliminar);
			//hacemos el commit
			session.getTransaction().commit();
			
			eliminacionExitosa = true;
		
		}catch (Exception e) {
			System.out.println("Excepción elinando operacion en la BD deleteOpe \n" + e.getMessage());
		}finally{
	        session.close();
	    }
	
		return eliminacionExitosa;
		
	}
	
}
