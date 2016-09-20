package com.ipartek.formacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dao.interfaces.EjemplarDAO;
import com.ipartek.formacion.dao.persistence.Ejemplar;
import com.ipartek.formacion.service.interfaces.EjemplarService;

@Service
public class EjemplarServiceImp implements EjemplarService {
	
	@Autowired
	EjemplarDAO ejemDAO;

	@Override
	public List<Ejemplar> getAll() {
		List<Ejemplar> ejemplares = ejemDAO.getAll();
		return ejemplares;
	}

	@Override
	public Ejemplar getByID(int id) {
		Ejemplar ejem = ejemDAO.getByID(id);
		return ejem;
	}

	@Override
	public Ejemplar update(Ejemplar ejemplar) {
		Ejemplar ejem = ejemDAO.update(ejemplar);
		return ejem;
	}

	@Override
	public void delete(int id) {
		ejemDAO.delete(id);

	}

	@Override
	public Ejemplar create(Ejemplar ejemplar) {
		return ejemDAO.create(ejemplar);
	}

}
