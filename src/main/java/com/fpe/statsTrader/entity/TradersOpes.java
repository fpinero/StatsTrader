package com.fpe.statsTrader.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="traders_opes")
@SessionScoped
@ManagedBean
public class TradersOpes {
	
		// annotate the class as an entity and map to db table
		
		// define fields
		
		// anotate the fields with db columns names
		
		// create constructors
		
		// genterate getter/seeters methods
		
		// generate toString method
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")
		private Integer id;
		
		@Column(name="trader_id")
		private Integer traderId;
		
		@Column(name="symbol")
		private String symbolTrade;
		
		@Column(name="side")
		private String sideTrade;
		
		@Column(name="resultado")
		private String resultadoTrade;  //BUENO, MALO, BREAKEVEN
		
		@Column(name="patron_1m")
		private String patronTrade1m;
		
		@Column(name="patron_15m")
		private String patronTrade15m;
		
		@Column(name="shares")
		private Integer sharesTrade;
		
		@Column(name="bruto_ope")
		private Double brutoOpe;
		
		@Column(name="neto_ope")
		private Double netoOpe;
		
		@Column(name="fecha")
		private Date fechaTrade;
		
		@Column(name="cents_trade")
		private Integer centsTrade;
		
		@Column(name="todo_100_plan")
		private boolean todo100plan;
		
		@Column(name="stop_evitable")
		private String stopEvitable;
		
		@Column(name="observaciones")
		private String observaciones;
		
		@Transient
		private List<String> sideItems;
		
		@Transient
		private List<String> resultadoItems;
		
		@Transient
		private List<String> patrones1m;
		
		@Transient
		private List<String> patrones15m;
		
		@Transient
		private Map<String, List<String>> dataSegunSide1m = new HashMap<String, List<String>>();
		
		@Transient
		private Map<String, List<String>> dataSegunSide15m = new HashMap<String, List<String>>();
		
		@Transient
		private List<String> patronLargoTrade1mItems;
		
		@Transient
		private List<String> patronCortoTrade1mItems;
		
		@Transient
		private List<String> patronLargoTrade15mItems;
		
		@Transient
		private List<String> patronCortoTrade15mItems;
		
		@Transient
		private List<String> todo100PlanItems;
		
		@Transient
		private List<String> stopEvitableItems;
		
		public TradersOpes() {
			
		}
		
		@PostConstruct
		public void init() {
			sideItems = new ArrayList<>();
			sideItems.add("Largo");
			sideItems.add("Corto");		
			
			resultadoItems = new ArrayList<>();
			resultadoItems.add("Bueno");
			resultadoItems.add("Stop");
			resultadoItems.add("BreakEven");
			
			patronLargoTrade1mItems = new ArrayList<>();
			patronLargoTrade1mItems.add("Doble Suelo");
			patronLargoTrade1mItems.add("BreakOut");
			patronLargoTrade1mItems.add("Cup&Handle");
			patronLargoTrade1mItems.add("HCH-inv");
			patronLargoTrade1mItems.add("Triángulo Alcista");
			patronLargoTrade1mItems.add("PBS");
			patronLargoTrade1mItems.add("Suelo Redondeado");
			patronLargoTrade1mItems.add("JBE");
			patronLargoTrade1mItems.add("Momentum");
			patronLargoTrade1mItems.add("Otro");
			dataSegunSide1m.put("Largo", patronLargoTrade1mItems);
			
			patronCortoTrade1mItems = new ArrayList<>();
			patronCortoTrade1mItems.add("Doble Techo");
			patronCortoTrade1mItems.add("BreakDown");
			patronCortoTrade1mItems.add("Cup&Handle-inv");
			patronCortoTrade1mItems.add("HCH");
			patronCortoTrade1mItems.add("Triángulo Bajista");
			patronCortoTrade1mItems.add("PSS");
			patronCortoTrade1mItems.add("Techo Redondeado");
			patronCortoTrade1mItems.add("DBI");
			patronCortoTrade1mItems.add("Momentum");
			patronCortoTrade1mItems.add("Otro");
			dataSegunSide1m.put("Corto", patronCortoTrade1mItems);
			
			patronLargoTrade15mItems = new ArrayList<>();
			patronLargoTrade15mItems.add("PBS");
			patronLargoTrade15mItems.add("Extendida a la baja con +COG");
			patronLargoTrade15mItems.add("Extendida a la baja");
			patronLargoTrade15mItems.add("Triángulo Alcista");
			patronLargoTrade15mItems.add("Triángulo");
			patronLargoTrade15mItems.add("Doble Suelo");
			patronLargoTrade15mItems.add("Breakout cont. Tendencia");
			patronLargoTrade15mItems.add("Otro");
			dataSegunSide15m.put("Largo", patronLargoTrade15mItems);
			
			patronCortoTrade15mItems = new ArrayList<>();
			patronCortoTrade15mItems.add("PSS");
			patronCortoTrade15mItems.add("Extendida al alza con -COG");
			patronCortoTrade15mItems.add("Extendida al alza");
			patronCortoTrade15mItems.add("Triángulo Bajista");
			patronCortoTrade15mItems.add("Triángulo");
			patronCortoTrade15mItems.add("Doble Techo");
			patronCortoTrade15mItems.add("Breakdown cont. Tendencia");
			patronCortoTrade15mItems.add("Otro");
			dataSegunSide15m.put("Corto", patronCortoTrade15mItems);
			
			todo100PlanItems = new ArrayList<>();
			todo100PlanItems.add("Sí");
			todo100PlanItems.add("No");
			
			stopEvitableItems = new ArrayList<>();
			stopEvitableItems.add("No fue stop");
			stopEvitableItems.add("Si");
			stopEvitableItems.add("No");
			
		}

		public TradersOpes(Integer traderId, String symbolTrade, String sideTrade, String resultadoTrade,
				String patronTrade1m, String patronTrade15m, Integer sharesTrade, Double brutoOpe, Double netoOpe,
				Date fechaTrade, Integer centsTrade, boolean todo100plan, String observaciones) {
			this.traderId = traderId;
			this.symbolTrade = symbolTrade;
			this.sideTrade = sideTrade;
			this.resultadoTrade = resultadoTrade;
			this.patronTrade1m = patronTrade1m;
			this.patronTrade15m = patronTrade15m;
			this.sharesTrade = sharesTrade;
			this.brutoOpe = brutoOpe;
			this.netoOpe = netoOpe;
			this.fechaTrade = fechaTrade;
			this.centsTrade = centsTrade;
			this.todo100plan = todo100plan;
			this.observaciones = observaciones;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getTraderId() {
			System.out.println("traderId=" + traderId);
			return traderId;
		}

		public void setTraderId(Integer traderId) {
			System.out.println("estableciendo traderId a: " + traderId);
			this.traderId = traderId;
		}

		public String getSymbolTrade() {
			return symbolTrade;
		}

		public void setSymbolTrade(String symbolTrade) {
			this.symbolTrade = symbolTrade.toUpperCase();
		}

		public String getSideTrade() {
			return sideTrade;
		}

		public void setSideTrade(String sideTrade) {
			this.sideTrade = sideTrade;
		}

		public String getResultadoTrade() {
			return resultadoTrade;
		}

		public void setResultadoTrade(String resultadoTrade) {
			this.resultadoTrade = resultadoTrade;
		}

		public String getPatronTrade1m() {
			return patronTrade1m;
		}

		public void setPatronTrade1m(String patronTrade1m) {
			this.patronTrade1m = patronTrade1m;
		}

		public String getPatronTrade15m() {
			return patronTrade15m;
		}

		public void setPatronTrade15m(String patronTrade15m) {
			this.patronTrade15m = patronTrade15m;
		}

		public Integer getSharesTrade() {
			return sharesTrade;
		}

		public void setSharesTrade(Integer sharesTrade) {
			this.sharesTrade = sharesTrade;
		}

		public Double getBrutoOpe() {
			return brutoOpe;
		}

		public void setBrutoOpe(Double brutoOpe) {
			this.brutoOpe = brutoOpe;
		}

		public Double getNetoOpe() {
			return netoOpe;
		}

		public void setNetoOpe(Double netoOpe) {
			this.netoOpe = netoOpe;
		}

		public Date getFechaTrade() {
			return fechaTrade;
		}

		public void setFechaTrade(Date fechaTrade) {
			this.fechaTrade = fechaTrade;
		}

		public Integer getCentsTrade() {
			return centsTrade;
		}

		public void setCentsTrade(Integer centsTrade) {
			this.centsTrade = centsTrade;
		}

		public boolean isTodo100plan() {
			return todo100plan;
		}

		public void setTodo100plan(boolean todo100plan) {
			this.todo100plan = todo100plan;
		}

		public String getObservaciones() {
			return observaciones;
		}

		public String getStopEvitable() {
			return stopEvitable;
		}

		public void setStopEvitable(String stopEvitable) {
			this.stopEvitable = stopEvitable;
		}

		public void setObservaciones(String observaciones) {
			this.observaciones = observaciones;
		}

		public List<String> getPatrones1m() {
			return patrones1m;
		}
		
		public List<String> getPatrones15m() {
			return patrones15m;
		}

		public List<String> getSideItems() {
			return sideItems;
		}
		
		public List<String> getResultadoItems() {
			return resultadoItems;
		}

		@Override
		public String toString() {
			return "TradersOpes [id=" + id + ", traderId=" + traderId + ", symbolTrade=" + symbolTrade + ", sideTrade="
					+ sideTrade + ", resultadoTrade=" + resultadoTrade + ", patronTrade1m=" + patronTrade1m
					+ ", patronTrade15m=" + patronTrade15m + ", sharesTrade=" + sharesTrade + ", brutoOpe=" + brutoOpe
					+ ", netoOpe=" + netoOpe + ", fechaTrade=" + fechaTrade + ", centsTrade=" + centsTrade
					+ ", todo100plan=" + todo100plan + ", stopEvitable=" + stopEvitable + ", observaciones="
					+ observaciones + "]";
		}

		public void initTraderId() {
			
			Trader currentTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
//			this.traderId = currentTrader.getId();
			setTraderId(currentTrader.getId());
			System.out.println("traderId=" + getTraderId());
		}

		public void test() {
			System.out.println("id=" + id + ", traderId=" + traderId + ", symbolTrade=" + symbolTrade + ", sideTrade="
					+ sideTrade + ", resultadoTrade=" + resultadoTrade + ", patronTrade1m=" + patronTrade1m
					+ ", patronTrade15m=" + patronTrade15m + ", sharesTrade=" + sharesTrade + ", brutoOpe=" + brutoOpe
					+ ", netoOpe=" + netoOpe + ", fechaTrade=" + fechaTrade + ", centsTrade=" + centsTrade
					+ ", todo100plan=" + todo100plan + ", stopEvitable=" + stopEvitable + ", observaciones="
					+ observaciones);
		}
		
		public void onSideChange() {
			if (sideTrade != null && !sideTrade.equals("")){
				patrones1m = dataSegunSide1m.get(sideTrade);
				patrones15m = dataSegunSide15m.get(sideTrade);
				System.out.println("Actualizando patrones disponbles para 1m segun side...");
				for (String p1 : patrones1m){
					System.out.println(p1);
				}
				System.out.println("Actualizando patrones disponbles para 15m segun side...");
				for (String p15 : patrones15m){
					System.out.println(p15);
				}
			} else {
				patrones1m = new ArrayList<>();
				patrones15m = new ArrayList<>();
			}
			
			
			
		}
		
}
