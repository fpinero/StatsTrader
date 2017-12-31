package com.fpe.statsTrader.entity;


import java.io.IOException;
import java.io.Serializable;

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

import com.fpe.statsTrader.utils.IsValidEmail;

@Entity
@Table(name="trader")
@SessionScoped
@ManagedBean
public class Trader implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4943463358467496013L;

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
	
	@Transient  //indica que no es un campo de la tabla
	private String textoErrorEmailInvalido;
	
	public Trader() {
		
	}

	public Trader(String nombre, String user, String password, String email, Double bP, Double costoPlataforma,
			Double objetivoSemanal, Double objetivoMensual, Double saldo, String objetivoFase,
			Double maxPerdidasSemanal, Double maxPerdidasMensual) {
		this.nombre = nombre;
		this.user = user;
		this.password = password;
		this.email = email;
		this.BP = bP;
		this.costoPlataforma = costoPlataforma;
		this.objetivoSemanal = objetivoSemanal;
		this.objetivoMensual = objetivoMensual;
		this.Saldo = saldo;
		this.objetivoFase = objetivoFase;
		this.maxPerdidasSemanal = maxPerdidasSemanal;
		this.maxPerdidasMensual = maxPerdidasMensual;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		System.out.println("Setting Trader id to: " + id);
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
		System.out.println("Setting Trader user to: " + user);
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

	public void setObejtivoSemanal(Double objetivoSemanal) {
		this.objetivoSemanal = objetivoSemanal;
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
	
	public String getTextoErrorEmailInvalido() {
		return textoErrorEmailInvalido;
	}

	public void setTextoErrorEmailInvalido(String textoErrorEmailInvalido) {
		this.textoErrorEmailInvalido = textoErrorEmailInvalido;
	}

	@Override
	public String toString() {
		return "Trader [id=" + id + ", nombre=" + nombre + ", user=" + user + ", password=" + password + ", email="
				+ email + ", BP=" + BP + ", costoPlataforma=" + costoPlataforma + ", obejtivoSemanal=" + objetivoSemanal
				+ ", objetivoMensual=" + objetivoMensual + ", Saldo=" + Saldo + ", objetivoFase=" + objetivoFase
				+ ", maxPerdidasSemanal=" + maxPerdidasSemanal + ", maxPerdidasMensual=" + maxPerdidasMensual + "]";
	}
	

	public String submitUserResponse() throws IOException{
		
		IsValidEmail isValidEmail = new IsValidEmail();
		//comprobar si la dirección de email contiene un dominio de email temporal
		if (isValidEmail.isAnEmailDisposable(email)) {
			setTextoErrorEmailInvalido("La dirección de email " + email + " no supera las reglas del firewall");
			return "emailinvalido?faces-redirect=true";
		}
		//compronar si el nombre de usuario ya está siendo usado por otro usuario
		if (isValidEmail.nombreUsuarioYaEnUso(user)) {
			setTextoErrorEmailInvalido("El nombre de usuario: " + user + " no está disponible");
			return "emailinvalido?faces-redirect=true";
		}
		//comprobar si dicha dirección de email ya esta en uso
		if (isValidEmail.EmailYaEnUso(email)) {
			setTextoErrorEmailInvalido("El email: " + email + " ya está en uso por otro usuario");
			return "emailinvalido?faces-redirect=true";
		}
		//comprobar si la dirección de email es válida
		boolean emailValido = isValidEmail.isValidEmailAddress(email);
		if (emailValido){
			//añadamos el objeto Trader a un attributo de la sesion 
			//para que sea accesible desde otras clases
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("thisTrader", this);
			setTextoErrorEmailInvalido("");
			return "enviarcodver?faces-redirect=true";
		} else {
			setTextoErrorEmailInvalido("El email " + email + " no cumple los requisitos de una dirección válida");
			return "emailinvalido?faces-redirect=true";
		}
	}  
	
	public void destruyeThisTrader() {
		//se usa a la hora de cerrar la sesión
		this.id = null;
		this.nombre = null;
		this.user = null;
		this.password = null;
		this.email = null;
		this.BP = null;
		this.costoPlataforma = null;
		this.Saldo = null;
		this.objetivoFase = null;
		this.objetivoSemanal = null;
		this.objetivoMensual = null;
		this.maxPerdidasSemanal = null;
		this.maxPerdidasMensual = null;
		
		System.out.println("Objeto Trader set a null todos sus campos...");
		
	}
	
	//Se encarga de cargar los datos del atributo de la sesion thisTrader en esta clase y que así se
	//muestren en el formulario de modificar cuenta usuario.
	public void initAfterLogin(){
		System.out.println("inicializando Trader si existe el parametro thisTrader en esta sesion...");
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader") != null) {
        	
        	Trader currentTrader = (Trader) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("thisTrader");
        	System.out.println("El parametro de sesion thisTrader NO es nulo, contiene este trader:\n" + currentTrader.toString());
        	
        	this.id = currentTrader.id;
        	this.nombre = currentTrader.nombre;
    		this.user = currentTrader.user;
    		this.password = currentTrader.password;
    		this.email = currentTrader.email;
    		this.BP = currentTrader.BP;
    		this.costoPlataforma = currentTrader.costoPlataforma;
    		this.objetivoSemanal = currentTrader.objetivoSemanal;
    		this.objetivoMensual = currentTrader.objetivoMensual;
    		this.Saldo = currentTrader.Saldo;
    		this.objetivoFase = currentTrader.objetivoFase;
    		this.maxPerdidasSemanal = currentTrader.maxPerdidasSemanal;
    		this.maxPerdidasMensual = currentTrader.maxPerdidasMensual;
        } else {
        	System.out.println("El parametro de sesion thisTrader es aún nulo...");
        }
    }
	
}
