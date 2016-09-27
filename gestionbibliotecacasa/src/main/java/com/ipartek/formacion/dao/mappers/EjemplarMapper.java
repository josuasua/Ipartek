package com.ipartek.formacion.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dao.persistence.Ejemplar;

public class EjemplarMapper implements RowMapper<Ejemplar> {

	@Override
	public Ejemplar mapRow(ResultSet rs, int arg1) throws SQLException {
		Ejemplar ejemplar = null;
		ejemplar = new Ejemplar();
		ejemplar.setId(rs.getInt("e.idEjemplar"));
		ejemplar.setAutor(rs.getString("l.autor"));
		ejemplar.setTitulo(rs.getString("l.titulo"));
		ejemplar.setEditorial(rs.getString("e.editorial"));
		ejemplar.setNumeropaginas(rs.getInt("e.numeropaginas"));
		
		return ejemplar;
	}

}
