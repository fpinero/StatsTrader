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
public class UpdateDatosOpe {
	
	//retorna true si todo va bien
	public boolean updateTraderOpe(TradersOpes opeModificada) {
		
		boolean actualizacionExitosa = false;
		
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
		}
	
		return actualizacionExitosa;
		
	}
	
}
