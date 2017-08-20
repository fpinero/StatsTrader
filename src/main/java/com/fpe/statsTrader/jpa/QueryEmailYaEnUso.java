package com.fpe.statsTrader.jpa;

import java.util.List;

import org.hibernate.Session;

import com.fpe.statsTrader.GlobalVars;
import com.fpe.statsTrader.entity.Trader;

public class QueryEmailYaEnUso {
	
	//retorna true si el email indicado ya está en uso
	public boolean isEmailInUse(String email) {
		
		boolean NombreEnUso = false;
		
		System.out.println("\nEmail a verificar si ya está en uso: " + email);
		
		// Creemos la sesión
		Session session = GlobalVars.factory.getCurrentSession();
		
		try {
			//comencemos la transaccion
			session.beginTransaction();
			
			//obtengamos la lista de traders con user = userName
			String query = "from Trader t WHERE t.email='" + email +"'";
			System.out.println("query=" + query);
			List<Trader> traders = session.createQuery(query).getResultList();
			
			//hagamos el commit
			session.getTransaction().commit();
			
			//Si la lista no está vacía es que el email indicado ya está siendo utilizado por otro usuario
			if (!traders.isEmpty()) {
				NombreEnUso = true;
				System.out.println("El Email: " + email +" ya está en uso por otro usuario");
			}
			
			
		} catch (Exception e) {
			System.out.println("Excepción en QueryEmailYaEnUso \n" + e.getMessage());
			return true;
		}
		
		return NombreEnUso;
		
	}

}
