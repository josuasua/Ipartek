package com.ipartek.formacion.dao.persistence;

public class Ejemplar extends Libro{
	
	private String editorial;
	private int id;
	private int numeropaginas;
	private Usuario usuario;
	
	


	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumeropaginas() {
		return numeropaginas;
	}
	public void setNumeropaginas(int numeropaginas) {
		this.numeropaginas = numeropaginas;
	}
	public Ejemplar() {
		super();
		setId(0);
		setEditorial("");
		setUsuario(new Usuario());
		setNumeropaginas(0);
	}
	public Ejemplar(String editorial, int id, int numeropaginas, Usuario usuario) {
		super();
		this.editorial = editorial;
		this.id = id;
		this.numeropaginas = numeropaginas;
		this.usuario = usuario;
	}

}
