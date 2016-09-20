package com.ipartek.formacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dao.interfaces.UsuarioDAO;
import com.ipartek.formacion.dao.persistence.Usuario;
import com.ipartek.formacion.service.interfaces.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService {
	
	@Autowired
	UsuarioDAO usuDAO;

	@Override
	public List<Usuario> getAll() {
		List<Usuario> usuarios = null;
		usuarios = usuDAO.getAll();
		return usuarios;
		// return usuDAO.getAll();
	}

	@Override
	public Usuario getByID(int id) {
		Usuario usuario = usuDAO.getByID(id);
		return usuario;
	}

	@Override
	public Usuario update(Usuario usuario) {
		Usuario usu = usuDAO.update(usuario);
		return usu;
	}

	@Override
	public void delete(int id) {
		usuDAO.delete(id);

	}

	@Override
	public Usuario create(Usuario usuario) {
		return usuDAO.create(usuario);
	}

}
