package com.ipartek.formacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dao.interfaces.LibroDAO;
import com.ipartek.formacion.dao.persistence.Libro;
import com.ipartek.formacion.service.interfaces.LibroService;

@Service
public class LibroServiceImp implements LibroService {
	
	@Autowired
	LibroDAO libDAO;

	@Override
	public List<Libro> getAll() {
		List<Libro> libros = libDAO.getAll();
		return libros;
	}

	@Override
	public Libro getByID(int id) {
		Libro lib = libDAO.getByID(id);
		return lib;
	}

	@Override
	public Libro update(Libro libro) {
		Libro lib = libDAO.update(libro);
		return lib;
	}

	@Override
	public void delete(int id) {
		libDAO.delete(id);

	}

	@Override
	public Libro create(Libro libro) {
		
		return libDAO.create(libro);
	}

}
