package com.fpe.statsTrader.jpa;

import javax.faces.context.FacesContext;

import org.hibernate.Session;

import com.fpe.statsTrader.GlobalVars;
import com.fpe.statsTrader.entity.Trader;

//@Component
//@ManagedBean
//@SessionScoped
public class UpdateDatosCuentaTrader {
	
	//retorna true si todo va bien
	public boolean updateTrader(Trader traderActualizado) {
		
		boolean actualizacionExitosa = false;
		
		System.out.println("Trader a actualizar en la BD: " + traderActualizado.toString());
		
		// Creemos la sesión
		Session session = GlobalVars.factory.getCurrentSession();
		
		try {
			//comenzamos la transacción
			session.beginTransaction();
			//actualizamos el objeto
			session.update(traderActualizado);
			//hacemos el commit
			session.getTransaction().commit();
			//imputamos los nuevos datos en el atributo thisTrader de la sesion
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("thisTrader", traderActualizado);
			
			actualizacionExitosa = true;
		
		}catch (Exception e) {
			System.out.println("Excepción actualizando trader en la BD UpdateDatosCuentaTrader \n" + e.getMessage());
		}
	
		return actualizacionExitosa;
		
	}
	
}
