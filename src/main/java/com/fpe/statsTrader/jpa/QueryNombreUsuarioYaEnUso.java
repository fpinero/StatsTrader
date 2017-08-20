package com.fpe.statsTrader.jpa;

import java.util.List;

import org.hibernate.Session;

import com.fpe.statsTrader.GlobalVars;
import com.fpe.statsTrader.entity.Trader;

public class QueryNombreUsuarioYaEnUso {
	
	//retorna true si el nombre de usuario elegido ya está en uso
	public boolean isUsernameInUse(String userName) {
		
		boolean NombreEnUso = false;
		
		System.out.println("\nNombre usuario a verificar si ya está en uso: " + userName);
		
		// Creemos la sesión
		Session session = GlobalVars.factory.getCurrentSession();
		
		try {
			//comencemos la transaccion
			session.beginTransaction();
			
			//obtengamos la lista de traders con user = userName
			String query = "from Trader t WHERE t.user='" + userName +"'";
			System.out.println("query=" + query);
			List<Trader> traders = session.createQuery(query).getResultList();
			
			//hagamos el commit
			session.getTransaction().commit();
			
			//Si la lista no está vacía es que el nombre de usuaio está en uso
			if (!traders.isEmpty()) {
				NombreEnUso = true;
				System.out.println("El nombre de usuario: " + userName +" ya está en uso por otro usuario");
			}
			
			
		} catch (Exception e) {
			System.out.println("Excepción en QueryNombreUsuarioYaEnUso \n" + e.getMessage());
			return true;
		}
		
		return NombreEnUso;
		
	}

}
