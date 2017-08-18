package com.fpe.statsTrader.entity;


import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fpe.statsTrader.utils.IsValidEmail;

@Entity
@Table(name="trader")
@ManagedBean
@SessionScoped
public class Trader {
	
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
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="user")
	private String user;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="bp")
	private Double BP;
	
	@Column(name="costo_plataforma")
	private Double costoPlataforma; 
	
	@Column(name="obejtivo_semanal")
	private Double objetivoSemanal;
	
	@Column(name="objetivo_mensual")
	private Double objetivoMensual;
	
	@Column(name="saldo")
	private Double Saldo;
	
	@Column(name="objetivo_fase")
	private String objetivoFase;
	
	@Column(name="max_perdidas_semanal")
	private Double maxPerdidasSemanal;
	
	@Column(name="max_perdidas_mensual")
	private Double maxPerdidasMensual;

	public Trader() {
		
	}

	public Trader(String nombre, String user, String password, String email, Double bP, Double costoPlataforma,
			Double obejtivoSemanal, Double objetivoMensual, Double saldo, String objetivoFase,
			Double maxPerdidasSemanal, Double maxPerdidasMensual) {
		this.nombre = nombre;
		this.user = user;
		this.password = password;
		this.email = email;
		this.BP = bP;
		this.costoPlataforma = costoPlataforma;
		this.objetivoSemanal = obejtivoSemanal;
		this.objetivoMensual = objetivoMensual;
		this.Saldo = saldo;
		this.objetivoFase = objetivoFase;
		this.maxPerdidasSemanal = maxPerdidasSemanal;
		this.maxPerdidasMensual = maxPerdidasMensual;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getBP() {
		return BP;
	}

	public void setBP(Double bP) {
		BP = bP;
	}

	public Double getCostoPlataforma() {
		return costoPlataforma;
	}

	public void setCostoPlataforma(Double costoPlataforma) {
		this.costoPlataforma = costoPlataforma;
	}

	public Double getObejtivoSemanal() {
		return objetivoSemanal;
	}

	public void setObejtivoSemanal(Double obejtivoSemanal) {
		this.objetivoSemanal = obejtivoSemanal;
	}

	public Double getObjetivoMensual() {
		return objetivoMensual;
	}

	public void setObjetivoMensual(Double objetivoMensual) {
		this.objetivoMensual = objetivoMensual;
	}

	public Double getSaldo() {
		return Saldo;
	}

	public void setSaldo(Double saldo) {
		Saldo = saldo;
	}

	public String getObjetivoFase() {
		return objetivoFase;
	}

	public void setObjetivoFase(String objetivoFase) {
		this.objetivoFase = objetivoFase;
	}

	public Double getMaxPerdidasSemanal() {
		return maxPerdidasSemanal;
	}

	public void setMaxPerdidasSemanal(Double maxPerdidasSemanal) {
		this.maxPerdidasSemanal = maxPerdidasSemanal;
	}

	public Double getMaxPerdidasMensual() {
		return maxPerdidasMensual;
	}

	public void setMaxPerdidasMensual(Double maxPerdidasMensual) {
		this.maxPerdidasMensual = maxPerdidasMensual;
	}
	
	@Override
	public String toString() {
		return "Trader [id=" + id + ", nombre=" + nombre + ", user=" + user + ", password=" + password + ", email="
				+ email + ", BP=" + BP + ", costoPlataforma=" + costoPlataforma + ", obejtivoSemanal=" + objetivoSemanal
				+ ", objetivoMensual=" + objetivoMensual + ", Saldo=" + Saldo + ", objetivoFase=" + objetivoFase
				+ ", maxPerdidasSemanal=" + maxPerdidasSemanal + ", maxPerdidasMensual=" + maxPerdidasMensual + "]";
	}
	

	public String submitUserResponse() throws IOException{
		
		//TODO
		//comprobar si la dirección de email contiene un dominio de email temporal
		//compronar si el nombre de usuario ya está siendo usado por otro usuario
		//comprobar si dicha dirección de email ya esta en uso
		//comprobar esto en cuando haya acceso a la base de datos
		IsValidEmail isValidEmail = new IsValidEmail();
		boolean emailValido = isValidEmail.isValidEmailAddress(email);
		if (emailValido){
			//añadamos el objeto Trader a un attributo de la sesion 
			//para que sea accesible desde otras clases
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("thisTrader", this);
			return "enviarcodver?faces-redirect=true";
		} else {
			return "emailinvalido?faces-redirect=true";
		}
	}  
	
}
