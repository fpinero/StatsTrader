package com.fpe.statsTrader.utils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ShowWaitYesOrNot {

	private boolean show;
	
	@PostConstruct
	public void init() {
		this.show = false;
		System.out.println("setting ShowWaitYesOrNot.show=false");
	}
	
	public ShowWaitYesOrNot() {
	
	}

	public boolean getShow() {
		System.out.println("En ShowWaitYesOrNot.getShow");
		System.out.println("show="+show);
		return show;
	}
	
	public void setShowToYes() {
		this.show = true;
		System.out.println("...en setShowToYes. | show=" + show);
		
	}
	
	public void setShowToNo() {
		this.show = false;
		System.out.println("...en setShowToNo. | show=" + show);
		
	}
	
	public boolean isShow() {
		System.out.println("En ShowWaitYesOrNot.isShow");
		System.out.println("show="+show);
		return show;
	}

	public void setShow(boolean show) {
		System.out.println("en setShow ");
//		if(show){
//			System.out.println("show está a=" + show + " Estableciendolo a false");
//			this.show = false;
//		} else {
//			System.out.println("show está a=" + show + " Estableciendolo a true");
//			this.show = true;
//		}
		this.show = show;
	}

	
	
}
