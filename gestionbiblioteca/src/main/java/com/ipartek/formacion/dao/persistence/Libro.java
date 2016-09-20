package com.ipartek.formacion.dao.persistence;

public class Libro {
	
	private String titulo;
	private String autor;
	private String isbn;
	private int id;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Libro(String titulo, String autor, String isbn, int id) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.id = id;
	}
	public Libro() {
		super();
		setId(0);
		setIsbn("");
		setTitulo("");
		setAutor("");
	}
	
	

}
