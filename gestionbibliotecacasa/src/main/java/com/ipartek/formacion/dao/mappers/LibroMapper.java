package com.ipartek.formacion.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dao.persistence.Libro;

public class LibroMapper implements RowMapper<Libro> {

	@Override
	public Libro mapRow(ResultSet rs, int arg1) throws SQLException {
		Libro libro = null;
		libro = new Libro();
		libro.setId(rs.getInt("idLibro"));
		libro.setTitulo(rs.getString("titulo"));
		libro.setAutor(rs.getString("autor"));
		libro.setIsbn(rs.getString("isbn"));

		return libro;
	}
}
