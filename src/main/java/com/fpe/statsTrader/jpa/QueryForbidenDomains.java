package com.fpe.statsTrader.jpa;

import java.util.List;

import org.hibernate.Session;

import com.fpe.statsTrader.GlobalVars;
import com.fpe.statsTrader.entity.ForbidenDomains;

public class QueryForbidenDomains {
	
	//retorna true si el email contiene un dominio temporal
	public boolean isEmailDisposable(String email) {
		
		boolean disposalDomainPresent = false;
		
		System.out.println("Email a verificar si contiene un dominio temporal: " + email);
		
		// Creemos la sesión
		Session session = GlobalVars.factory.getCurrentSession();
		
		try {
			//comencemos la transaccion
			session.beginTransaction();
			
			//obtengamos la lista de dominios prohibidos
			List<ForbidenDomains> forbidensDomains = session.createQuery("from ForbidenDomains").getResultList();
			
			//hagamos el commit
			session.getTransaction().commit();
			
			//temporal: listemos los dominios leidos
//			for (ForbidenDomains fd : forbidensDomains){
//				System.out.println(fd);
//			}
			//comprobemos si el email contiene alguno de los dominios prohibidos
			for (ForbidenDomains fd : forbidensDomains){
				if (email.contains(fd.getDomain())) {
					disposalDomainPresent = true;
					System.out.println("Detectado dominio temporal " + fd.getDomain() + 
							" Rechazando dirección de email: " + email);
				}
			}
			
		} catch (Exception e) {
			System.out.println("Excepción en QueryForbidenDomians \n" + e.getMessage());
			return true;
		}
		
		return disposalDomainPresent;
		
	}

}
