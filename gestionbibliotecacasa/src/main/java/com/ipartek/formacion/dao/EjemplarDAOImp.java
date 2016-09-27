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

import com.ipartek.formacion.dao.interfaces.EjemplarDAO;
import com.ipartek.formacion.dao.mappers.EjemplarMapper;
import com.ipartek.formacion.dao.mappers.LibroMapper;
import com.ipartek.formacion.dao.mappers.UsuarioMapper;
import com.ipartek.formacion.dao.persistence.Ejemplar;
import com.ipartek.formacion.dao.persistence.Libro;
import com.ipartek.formacion.dao.persistence.Usuario;

@Repository
public class EjemplarDAOImp implements EjemplarDAO {

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
	public List<Ejemplar> getAll() {
		List<Ejemplar> ejemplares = null;
		/*
		jdbcCall.withProcedureName("getAllEjemplar");
		SqlParameterSource in = new MapSqlParameterSource();
		try{
			Map<String, Object> out = jdbcCall.execute(in);
			ejemplares = (List<Ejemplar>) out;
		}catch(EmptyResultDataAccessException e){
			ejemplares = new ArrayList<Ejemplar>();
		}catch (Exception e){
			
		}
		*/
		final String SQL = "SELECT e.idEjemplar, e.editorial, e.numeropaginas, l.titulo, l.autor FROM ejemplar e INNER JOIN libro l ON e.idLibro=l.idLibro";
		try {
			ejemplares = jdbcTemplate.query(SQL, new EjemplarMapper());
		}catch(EmptyResultDataAccessException e){
			ejemplares = new ArrayList<Ejemplar>();
			System.out.println("falla");
		}catch (Exception e){
			System.out.println("falla");
		}
		return ejemplares;
	}

	@Override
	public Ejemplar create(Ejemplar ejemplar) {
		
		jdbcCall.withProcedureName("insertEjemplar"); // usando las rutinas / procedures creadas en la BBDD
		SqlParameterSource in = new MapSqlParameterSource()
			.addValue("editorial", ejemplar.getEditorial())
			.addValue("numeropaginas", ejemplar.getNumeropaginas());
		Map<String, Object> out =jdbcCall.execute(in);
		ejemplar.setId((Integer) out.get("codigo"));
				/*
				 * SqlparameterSource es la clase de tipo Map en la cual se guardan los parametros del procedimiento almacenado.
				 * execute lanza la sentencia. en el out obtendremos la respuestas
				 */
		return ejemplar;
	}

	@Override
	public Ejemplar getByID(int id) {
		/*
		jdbcCall.withProcedureName("getbyIDEjemplar");
		Ejemplar ejemplar = null;
		SqlParameterSource in = new MapSqlParameterSource().addValue("id", id);
		try{
			Map<String, Object> out = jdbcCall.execute(in);
			ejemplar = (Ejemplar) out;
		}catch(EmptyResultDataAccessException e){
			ejemplar = new Ejemplar();
		}catch (Exception e){
			
		}
		return ejemplar;
		*/
		Ejemplar ejemplar = null;
		final String SQL = "SELECT e.idEjemplar, e.editorial, e.numeropaginas, l.autor, l.titulo FROM ejemplar e INNER JOIN libro l ON e.idLibro= l.idLibro WHERE idEjemplar = ?";
		try {
			ejemplar = jdbcTemplate.queryForObject(SQL, new Object[] { id }, new EjemplarMapper());
		}catch(EmptyResultDataAccessException e){
			ejemplar = new Ejemplar();
		}catch (Exception e){
			
		}
		
		return ejemplar;
	}

	@Override
	public Ejemplar update(Ejemplar ejemplar) {
		
		jdbcCall.withProcedureName("updateEjemplar"); // usando las rutinas / procedures creadas en la BBDD
		
		SqlParameterSource in = new MapSqlParameterSource()
			.addValue("editorial", ejemplar.getEditorial())
			.addValue("numeropaginas", ejemplar.getNumeropaginas());
		Map<String, Object> out =jdbcCall.execute(in);
		
		return ejemplar;
	}

	@Override
	public void delete(int id) {

		jdbcCall.withProcedureName("deleteEjemplar"); // usando las rutinas / procedures creadas en la BBDD
		
		SqlParameterSource in = new MapSqlParameterSource().addValue("id", id);
		Map<String, Object> out =jdbcCall.execute(in);

	}

	@Override
	public Ejemplar getEjemplarReservado(int id) {
		Ejemplar ejemplar = null;
		
		final String SQL = "SELECT e.idEjemplar, l.titulo, l.autor, e.editorial, e.numeropaginas FROM ejemplar e INNER JOIN libro l ON e.idLibro=l.idLibro WHERE e.idUsuario = ?";
		try {
			ejemplar = jdbcTemplate.queryForObject(SQL, new Object[] { id }, new EjemplarMapper());
		}catch(EmptyResultDataAccessException e){
			ejemplar = new Ejemplar();
			System.out.println("falla result data");
		}catch (Exception e){
			ejemplar = new Ejemplar();
		}
		
		return ejemplar;
	}
	
	
	@Override
	public List<Ejemplar> getAllDisponibles() {
		List<Ejemplar> ejemplares = null;
		
		final String SQL = "SELECT e.idEjemplar, l.titulo, l.autor, e.editorial, e.numeropaginas, e.contador FROM ejemplar e INNER JOIN libro l ON e.idLibro=l.idLibro WHERE e.contador > 0";
		try {
			ejemplares = jdbcTemplate.query(SQL, new EjemplarMapper());
		}catch(EmptyResultDataAccessException e){
			ejemplares = new ArrayList<Ejemplar>();
			System.out.println("falla");
		}catch (Exception e){
			
		}
		
		return ejemplares;
	}

}
