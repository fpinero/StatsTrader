package com.fpe.statsTrader.utils;

import java.util.Date;

import com.fpe.statsTrader.jpa.QueryPieOpes;
import com.fpe.statsTrader.jpa.QueryTraderRatio;

public class TraderRatio {
	
	private double ratio = 0.0;
	private double ratioSinStopsEvitables = 0.0;
	private double ratioTodoAcordePlan = 0.0;
	private int opesBuenas = 0;
	private int opesStop = 0;
	private int opesBe = 0;
	private int opesStopEvitable = 0;
	private int opesStopNoEvitable = 0;
	private int opesTodoAcordePlan = 0;
	private int opesNoTodoAcordePlan = 0;
	private int opesBuenasTodoAcordePlan = 0;
	private int opesBuenasNoTodoAcordePlan = 0;
	
	public TraderRatio() {
		
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public double getRatioSinStopsEvitables() {
		return ratioSinStopsEvitables;
	}

	public void setRatioSinStopsEvitables(double ratioSinStopsEvitables) {
		this.ratioSinStopsEvitables = ratioSinStopsEvitables;
	}

	public int getOpesBuenas() {
		return opesBuenas;
	}

	public void setOpesBuenas(int opesBuenas) {
		this.opesBuenas = opesBuenas;
	}

	public int getOpesStop() {
		return opesStop;
	}

	public void setOpesStop(int opesStop) {
		this.opesStop = opesStop;
	}

	public int getOpesBe() {
		return opesBe;
	}

	public void setOpesBe(int opesBe) {
		this.opesBe = opesBe;
	}

	public int getOpesStopEvitable() {
		return opesStopEvitable;
	}

	public void setOpesStopEvitable(int opesStopEvitable) {
		this.opesStopEvitable = opesStopEvitable;
	}
	
	public double getRatioTodoAcordePlan() {
		return ratioTodoAcordePlan;
	}

	public void setRatioTodoAcordePlan(double ratioTodoAcordePlan) {
		this.ratioTodoAcordePlan = ratioTodoAcordePlan;
	}

	public int getOpesStopNoEvitable() {
		return opesStopNoEvitable;
	}

	public void setOpesStopNoEvitable(int opesStopNoEvitable) {
		this.opesStopNoEvitable = opesStopNoEvitable;
	}

	public int getOpesTodoAcordePlan() {
		return opesTodoAcordePlan;
	}

	public void setOpesTodoAcordePlan(int opesTodoAcordePlan) {
		this.opesTodoAcordePlan = opesTodoAcordePlan;
	}

	public int getOpesNoTodoAcordePlan() {
		return opesNoTodoAcordePlan;
	}

	public void setOpesNoTodoAcordePlan(int opesNoTodoAcordePlan) {
		this.opesNoTodoAcordePlan = opesNoTodoAcordePlan;
	}

	public int getOpesBuenasTodoAcordePlan() {
		return opesBuenasTodoAcordePlan;
	}

	public void setOpesBuenasTodoAcordePlan(int opesBuenasTodoAcordePlan) {
		this.opesBuenasTodoAcordePlan = opesBuenasTodoAcordePlan;
	}

	public int getOpesBuenasNoTodoAcordePlan() {
		return opesBuenasNoTodoAcordePlan;
	}

	public void setOpesBuenasNoTodoAcordePlan(int opesBuenasNoTodoAcordePlan) {
		this.opesBuenasNoTodoAcordePlan = opesBuenasNoTodoAcordePlan;
	}

	public TraderRatio obtenDatosRatio(Date fechaInicial, Date fechaFinal) {
		//lanza la query por las opes y los ratios del trader logueado
		QueryTraderRatio queryTraderRatio = new QueryTraderRatio();
		TraderRatio traderRatio = queryTraderRatio.obtenRatio(fechaInicial, fechaFinal);
		this.ratio = traderRatio.getRatio();
		this.ratioSinStopsEvitables = traderRatio.getRatioSinStopsEvitables();
		this.ratioTodoAcordePlan = traderRatio.getRatioTodoAcordePlan();
		this.opesBuenas = traderRatio.getOpesBuenas();
		this.opesStop = traderRatio.getOpesStop();
		this.opesBe = traderRatio.getOpesBe();
		this.opesStopEvitable = traderRatio.getOpesStopEvitable();
		this.opesStopNoEvitable = traderRatio.getOpesStopNoEvitable();
		this.opesTodoAcordePlan = traderRatio.getOpesTodoAcordePlan();
		this.opesNoTodoAcordePlan = traderRatio.getOpesNoTodoAcordePlan();
		this.opesBuenasTodoAcordePlan = traderRatio.getOpesBuenasTodoAcordePlan();
		this.opesBuenasNoTodoAcordePlan = traderRatio.getOpesBuenasNoTodoAcordePlan();
		
		System.out.println("TraderRatio.this=" + this.toString());
		return this;
	}

	@Override
	public String toString() {
		return "TraderRatio [ratio=" + ratio + ", ratioSinStopsEvitables=" + ratioSinStopsEvitables
				+ ", ratioTodoAcordePlan=" + ratioTodoAcordePlan + ", opesBuenas=" + opesBuenas + ", opesStop="
				+ opesStop + ", opesBe=" + opesBe + ", opesStopEvitable=" + opesStopEvitable + ", opesStopNoEvitable="
				+ opesStopNoEvitable + ", opesTodoAcordePlan=" + opesTodoAcordePlan + ", opesNoTodoAcordePlan="
				+ opesNoTodoAcordePlan + ", opesBuenasTodoAcordePlan=" + opesBuenasTodoAcordePlan
				+ ", opesBuenasNoTodoAcordePlan=" + opesBuenasNoTodoAcordePlan + "]";
	}

	
	
}
