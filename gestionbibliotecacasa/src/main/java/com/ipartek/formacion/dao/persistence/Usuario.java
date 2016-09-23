package com.ipartek.formacion.dao.persistence;

import java.util.Date;



public class Usuario {
	
	private String nombre;
	private String apellidos;
	private String email;
	private String password;
	private Date fNacimiento;
	private int id;
	private int idEjemplar;
	
	
	
	public int getIdEjemplar() {
		return idEjemplar;
	}
	public void setIdEjemplar(int idEjemplar) {
		this.idEjemplar = idEjemplar;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getfNacimiento() {
		return fNacimiento;
	}
	public void setfNacimiento(Date fNacimiento) {
		this.fNacimiento = fNacimiento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Usuario() {
		super();
		setId(0);
		setIdEjemplar(0);
		setNombre("");
		setApellidos("");
		setEmail("");
		setPassword("");
		setfNacimiento(new Date());
	}
	
	public Usuario(String nombre, String apellidos, String email,
			String password, Date fNacimiento, int id, int idEjemplar) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.fNacimiento = fNacimiento;
		this.id = id;
		this.idEjemplar = idEjemplar;
	}

}
