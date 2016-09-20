package com.ipartek.formacion.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dao.persistence.Ejemplar;

public interface EjemplarDAO {

	public List<Ejemplar> getAll();

	public Ejemplar create(Ejemplar ejemplar);

	public Ejemplar getByID(int id);

	public Ejemplar update(Ejemplar ejemplar);

	public void delete(int id);
}
