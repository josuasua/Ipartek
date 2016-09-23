package com.ipartek.formacion.dao.persistence;

public class Ejemplar extends Libro{
	
	private String editorial;
	private int id;
	private int numeropaginas;
	private int contador;
	
	

	public int getContador() {
		return contador;
	}
	public void setContador(int contador) {
		this.contador = contador;
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
		setNumeropaginas(0);
		setContador(0);
	}
	public Ejemplar(String editorial, int id, int numeropaginas, int contador) {
		super();
		this.editorial = editorial;
		this.id = id;
		this.numeropaginas = numeropaginas;
		this.contador = contador;
	}

}
