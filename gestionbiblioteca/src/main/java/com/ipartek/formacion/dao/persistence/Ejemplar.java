package com.ipartek.formacion.dao.persistence;

public class Ejemplar {
	
	private String editorial;
	private int id;
	private int numeropaginas;
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
		setNumeropaginas(0);
	}
	public Ejemplar(String editorial, int id, int numeropaginas) {
		super();
		this.editorial = editorial;
		this.id = id;
		this.numeropaginas = numeropaginas;
	}

}
