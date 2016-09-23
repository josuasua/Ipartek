package com.ipartek.formacion.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.dao.interfaces.UsuarioDAO;
import com.ipartek.formacion.dao.mappers.LibroMapper;
import com.ipartek.formacion.dao.mappers.UsuarioMapper;
import com.ipartek.formacion.dao.persistence.Libro;
import com.ipartek.formacion.dao.persistence.Usuario;

@Repository
public class UsuarioDAOImp implements UsuarioDAO {
	
	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate; //usamos todo el rato rutinas, lo tenemos por si algo falla.
	private SimpleJdbcCall jdbcCall;

	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.jdbcCall = new SimpleJdbcCall(dataSource);

	}
	
	@Override
	public List<Usuario> getAll() {
		List<Usuario> usuarios = null;
		/*
		jdbcCall.withProcedureName("getAllUsuario");
		SqlParameterSource in = new MapSqlParameterSource();
		try{
			Map<String, Object> out = jdbcCall.execute(in);
			usuarios = (List<Usuario>) out;
		}catch(EmptyResultDataAccessException e){
			usuarios = new ArrayList<Usuario>();
		}catch (Exception e){
			
		}
		*/
		
		final String SQL = "SELECT idUsuario, nombre, apellidos FROM usuario";
		try {
			usuarios = jdbcTemplate.query(SQL, new UsuarioMapper());
		}catch(EmptyResultDataAccessException e){
			usuarios = new ArrayList<Usuario>();
			System.out.println("falla");
		}catch (Exception e){
			System.out.println("falla");
		}
		return usuarios;
	}

	@Override
	public Usuario create(Usuario usuario) {
		jdbcCall.withProcedureName("insertUsuario"); // usando las rutinas / procedures creadas en la BBDD
		SqlParameterSource in = new MapSqlParameterSource()
			.addValue("nombre", usuario.getNombre())
			.addValue("apellidos", usuario.getApellidos())
			.addValue("fNacimiento",usuario.getfNacimiento())
			.addValue("password", usuario.getPassword())
			.addValue("email", usuario.getEmail());
		Map<String, Object> out =jdbcCall.execute(in);
		
		usuario.setId((Integer) out.get("idUsuario"));
				/*
				 * SqlparameterSource es la clase de tipo Map en la cual se guardan los parametros del procedimiento almacenado.
				 * execute lanza la sentencia. en el out obtendremos la respuestas
				 */
		return usuario;
	}

	@Override
	public Usuario getByID(int id) {
		jdbcCall.withProcedureName("getbyIDUsuario");
		Usuario usuario = null;
		SqlParameterSource in = new MapSqlParameterSource().addValue("idUsuario", id);
		try{
			Map<String, Object> out = jdbcCall.execute(in);
			usuario = (Usuario) out;
		}catch(EmptyResultDataAccessException e){
			usuario = new Usuario();
		}catch (Exception e){
			
		}
		return usuario;
	}

	@Override
	public Usuario update(Usuario usuario) {
		
		jdbcCall.withProcedureName("updateUsuario"); // usando las rutinas / procedures creadas en la BBDD
		
		SqlParameterSource in = new MapSqlParameterSource()
			.addValue("nombre", usuario.getNombre())
			.addValue("apellidos", usuario.getApellidos())
			.addValue("fNacimiento",usuario.getfNacimiento())
			.addValue("password", usuario.getPassword())
			.addValue("email", usuario.getEmail());
		Map<String, Object> out =jdbcCall.execute(in);
		
		return usuario;
	}

	@Override
	public void delete(int id) {
		
		jdbcCall.withProcedureName("deleteUsuario"); // usando las rutinas / procedures creadas en la BBDD
		
		SqlParameterSource in = new MapSqlParameterSource().addValue("codigo", id);
		Map<String, Object> out =jdbcCall.execute(in);

	}

}
