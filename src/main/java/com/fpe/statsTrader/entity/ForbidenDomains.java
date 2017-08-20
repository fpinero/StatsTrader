package com.fpe.statsTrader.entity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="forbidendomains")
@ManagedBean
@SessionScoped
public class ForbidenDomains {

	// annotate the class as an entity and map to db table
	
	// define fields
	
	// anotate the fields with db columns names
	
	// create constructors
	
	// genterate getter/seeters methods
	
	// generate toString method
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="domain")
	private String domain;
	
	//No necesita otro costructor, esta tabla sólo se usa en modo lectura
	public ForbidenDomains() {
		
	}

	public int getId() {
		return id;
	}

	public String getDomain() {
		return domain;
	}

	@Override
	public String toString() {
		return "ForbidenDomains [id=" + id + ", domain=" + domain + "]";
	}
	
	
	
}
