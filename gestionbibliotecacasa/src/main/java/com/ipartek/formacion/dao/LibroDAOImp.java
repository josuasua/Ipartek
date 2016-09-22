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

import com.ipartek.formacion.dao.interfaces.LibroDAO;
import com.ipartek.formacion.dao.mappers.LibroMapper;
import com.ipartek.formacion.dao.persistence.Libro;


@Repository
public class LibroDAOImp implements LibroDAO {

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
	public List<Libro> getAll() {
		List<Libro> libros = null;
		/*
		jdbcCall.withProcedureName("getAllLibro");
		SqlParameterSource in = new MapSqlParameterSource();
		
		try{
			Map<String, Object> out = jdbcCall.execute(in);
			System.out.println("hola?");
			libros = (List<Libro>) out;*/
		final String SQL = "SELECT id, titulo, autor, isbn FROM libro";
		try {
			libros = jdbcTemplate.query(SQL, new LibroMapper());
		}catch(EmptyResultDataAccessException e){
			libros = new ArrayList<Libro>();
			System.out.println("falla");
		}catch (Exception e){
			
		}
		
		return libros;
	}

	@Override
	public Libro create(Libro libro) {
		jdbcCall.withProcedureName("insertLibro"); // usando las rutinas / procedures creadas en la BBDD
		SqlParameterSource in = new MapSqlParameterSource()
			.addValue("titulo", libro.getTitulo())
			.addValue("autor", libro.getAutor())
			.addValue("isbn",libro.getIsbn());
		Map<String, Object> out =jdbcCall.execute(in);
		libro.setId((Integer) out.get("codigo"));
				/*
				 * SqlparameterSource es la clase de tipo Map en la cual se guardan los parametros del procedimiento almacenado.
				 * execute lanza la sentencia. en el out obtendremos la respuestas
				 */
		return libro;
	}

	@Override
	public Libro getByID(int id) {
		/*
		jdbcCall.withProcedureName("getbyIDLibro");
		Libro libro = null;
		SqlParameterSource in = new MapSqlParameterSource().addValue("id", id);
		try{
			Map<String, Object> out = jdbcCall.execute(in);
			libro = (Libro) out;
		}catch(EmptyResultDataAccessException e){
			libro = new Libro();
		}catch (Exception e){
			
		}*/
		
		Libro libro = null;

		final String SQL = "SELECT id, titulo, autor, isbn FROM libro WHERE id=?";
		try {
			libro = jdbcTemplate.queryForObject(SQL, new Object[] { id }, new LibroMapper());
		} catch (EmptyResultDataAccessException e) {
			libro = new Libro();
		} catch (Exception e) {

		}

		return libro;
	}

	@Override
	public Libro update(Libro libro) {
		
		jdbcCall.withProcedureName("updateLibro"); // usando las rutinas / procedures creadas en la BBDD
		SqlParameterSource in = new MapSqlParameterSource()
			.addValue("codigo", libro.getId())
			.addValue("titulo", libro.getTitulo())
			.addValue("autor", libro.getAutor())
			.addValue("isbn",libro.getIsbn());
		Map<String, Object> out =jdbcCall.execute(in);
		
		return libro;
	}

	@Override
	public void delete(int id) {
		
		jdbcCall.withProcedureName("deleteLibro"); // usando las rutinas / procedures creadas en la BBDD
		
		SqlParameterSource in = new MapSqlParameterSource().addValue("codigo", id);
		Map<String, Object> out =jdbcCall.execute(in);

	}

}
