package com.ipartek.formacion.dao.interfaces;

import java.util.List;

import javax.sql.DataSource;

import com.ipartek.formacion.dao.persistence.Libro;

public interface LibroDAO {

	public List<Libro> getAll();

	public Libro create(Libro libro);

	public Libro getByID(int id);

	public Libro update(Libro libro);

	public void delete(int id);
	
	public void setDataSource(DataSource dataSource);
}
