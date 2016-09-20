package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dao.persistence.Ejemplar;

public interface EjemplarService {
	
	public List<Ejemplar> getAll();

	public Ejemplar getByID(int id);

	public Ejemplar update(Ejemplar ejemplar);

	public void delete(int id);
	
	public Ejemplar create (Ejemplar ejemplar);

}
