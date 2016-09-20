package com.ipartek.formacion.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dao.persistence.Usuario;

public interface UsuarioDAO{

	public List<Usuario> getAll();

	public Usuario create(Usuario usuario);

	public Usuario getByID(int id);

	public Usuario update(Usuario usuario);

	public void delete(int id);
}
