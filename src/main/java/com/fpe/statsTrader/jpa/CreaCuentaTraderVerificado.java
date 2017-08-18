package com.fpe.statsTrader.jpa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.fpe.statsTrader.GlobalVars;
import com.fpe.statsTrader.entity.Trader;

@Component
@ManagedBean
@SessionScoped
public class CreaCuentaTraderVerificado {
	
	public void saveTrader() {
		// Objeto a salvar
		Trader tmpTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
		
		System.out.println("Trader a ingresar en la BD: " + tmpTrader.toString());
		
		// Creemos la sesión
		Session session = GlobalVars.factory.getCurrentSession();
		
		try {
			//comenzamos la transacción
			session.beginTransaction();
			//salvamos el objeto
			session.save(tmpTrader);
			//hacemos el commit
			session.getTransaction().commit();
		
		}catch (Exception e) {
			System.out.println("Excepción salvando trader a la BD \n" + e.getMessage());
		}
	
	}
	
}
