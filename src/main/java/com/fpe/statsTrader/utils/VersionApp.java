package com.fpe.statsTrader.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

@ManagedBean
@Component
@SessionScoped
public class VersionApp {

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
