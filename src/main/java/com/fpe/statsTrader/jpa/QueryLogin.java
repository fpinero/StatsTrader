package com.fpe.statsTrader.jpa;

import java.util.List;

import javax.faces.context.FacesContext;

import org.hibernate.Session;

import com.fpe.statsTrader.GlobalVars;
import com.fpe.statsTrader.entity.Trader;

public class QueryLogin {
	
	//retorna true si las credenciales son válidas
	public boolean isUserPwdCorrecto(String user, String pwd) {
		
		boolean credencialesValidas = false;
		
		System.out.println("\nVerificando si : " + user + " + " + pwd + " son credenciales válidas");
		
		// Creemos la sesión
		Session session = GlobalVars.factory.getCurrentSession();
		
		try {
			//comencemos la transaccion
			session.beginTransaction();
			
			//obtengamos la lista de traders con user y pwd = a los indicados
			String query = "from Trader t WHERE t.user='" + user +"' AND t.password='" + pwd + "'";
			System.out.println("query=" + query);
			List<Trader> traders = session.createQuery(query).getResultList();
			
			//hagamos el commit
			session.getTransaction().commit();
			
			//Si la lista está vacía es que las credenciales son inválidas
			if (traders.isEmpty()) {
				credencialesValidas = false;
				System.out.println("Las credenciales indicadas no correponden a ningun usuario regstrado");
			} else {
				//existe un trader con esas credenciales
				Trader trader = traders.get(0);  //solo puede haber uno, asi q siempre es el index 0.
				System.out.println("Trader localizado: " + trader.toString());
				System.out.println("Id del trader=" + traders.get(0).getId());
				trader.setId(traders.get(0).getId());
				//añadamos el trader al parametro de la sesion
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("thisTrader", trader);
				credencialesValidas = true;
			}
			
			
		} catch (Exception e) {
			System.out.println("Excepción en isUserPwdCorrecto \n" + e.getMessage());
			return false;
		}
		
		return credencialesValidas;
		
	}

}
