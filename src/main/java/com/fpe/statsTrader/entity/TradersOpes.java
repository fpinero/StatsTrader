package com.fpe.statsTrader.entity;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
		private Integer TraderId;
		
		@Column(name="symbol")
		private String symbolTrade;
		
		@Column(name="side")
		private String sideTrade;
		
		@Column(name="resultado")
		private Double resultadoTrade;
		
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
		
		@Column(name="observaciones")
		private String observaciones;
		
		public TradersOpes() {
			
		}

		public TradersOpes(Integer traderId, String symbolTrade, String sideTrade, Double resultadoTrade,
				String patronTrade1m, String patronTrade15m, Integer sharesTrade, Double brutoOpe, Double netoOpe,
				Date fechaTrade, Integer centsTrade, boolean todo100plan, String observaciones) {
			TraderId = traderId;
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
			return TraderId;
		}

		public void setTraderId(Integer traderId) {
			TraderId = traderId;
		}

		public String getSymbolTrade() {
			return symbolTrade;
		}

		public void setSymbolTrade(String symbolTrade) {
			this.symbolTrade = symbolTrade;
		}

		public String getSideTrade() {
			return sideTrade;
		}

		public void setSideTrade(String sideTrade) {
			this.sideTrade = sideTrade;
		}

		public Double getResultadoTrade() {
			return resultadoTrade;
		}

		public void setResultadoTrade(Double resultadoTrade) {
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

		public void setObservaciones(String observaciones) {
			this.observaciones = observaciones;
		}
		

}
