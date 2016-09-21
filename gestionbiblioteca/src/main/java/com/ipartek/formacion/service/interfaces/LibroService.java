package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dao.LibroDAOImp;
import com.ipartek.formacion.dao.persistence.Libro;

public interface LibroService {

	public List<Libro> getAll();

	public Libro getByID(int id);

	public Libro update(Libro libro);

	public void delete(int id);
	
	public Libro create (Libro libro);
	
	public void setLibDAO(LibroDAOImp libDAO);
}
