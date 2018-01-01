package com.fpe.statsTrader.jpa;

import java.util.List;

import org.hibernate.Session;

import com.fpe.statsTrader.GlobalVars;
import com.fpe.statsTrader.entity.Trader;
import com.fpe.statsTrader.utils.SimpleMail;

public class QueryNombreUsuario {
	
	//retorna true si el nombre de usuario existe y por lo tanto podemos acceder a su email y pwd
	public boolean existeElUsuario(String userName) {
		
		boolean usuarioExiste = false;
		
		System.out.println("\nNombre usuario a verificar si existe para recuperar su password: " + userName);
		
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
			
			//Si la lista no está vacía es que el nombre de usuario es válido
			if (!traders.isEmpty()) {
				usuarioExiste = true;
				System.out.println("El nombre de usuario: " + userName +" es válido");
				Trader trader = traders.get(0); // solo puede haber un registro
				String traderEmail = trader.getEmail();
				String traderPwd = trader.getPassword();
				SimpleMail sendEmail = new SimpleMail();
				String textoEmail = "Recuperacion password para StatsTrader" + "\n\n"
	                    + "Su password es: " + traderPwd 
	                    + "\n\n\n" + "Si no ha solicitado recuperar su password, por favor ignore este email "
	                    + "y disculpe las molestias.";
				System.out.println("Enviando email para recuperar pwd a: " + traderEmail);
				System.out.println("Texto mensaje:\n" + textoEmail);
				sendEmail.send(traderEmail, "Statstrader recuperacion password.", textoEmail);	
				System.out.println("email enviado...");
			}
			
		} catch (Exception e) {
			System.out.println("Excepción en QueryNombreUsuario \n" + e.getMessage());
			return false;
		}finally{
	        session.close();
	    }
		
		return usuarioExiste;
		
	}

}
