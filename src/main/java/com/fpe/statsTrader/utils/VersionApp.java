package com.fpe.statsTrader.utils;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
//@Component
@SessionScoped
public class VersionApp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6914085008597835321L;
	private String versionApp;
	
	public VersionApp() {
		
	}

	public String getVersionApp() {
		return versionApp;
	}
	
	public VersionApp(String versionApp) {
		this.versionApp = versionApp;
	}

	public void setVersionApp(String versionApp) {
		this.versionApp = versionApp;
		System.out.println("Estableciendo version de la aplicacion a la: " + versionApp);
	}
	
	@Override
	public String toString() {
		return "VersionApp [versionApp=" + versionApp + "]";
	}
	
	
	
}
