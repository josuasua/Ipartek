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
		ejemplar.setId(rs.getInt("idEjemplar"));
		ejemplar.setAutor(rs.getString("autor"));
		ejemplar.setTitulo(rs.getString("titulo"));
		ejemplar.setEditorial(rs.getString("editorial"));
		ejemplar.setNumeropaginas(rs.getInt("numeropaginas"));
		
		return ejemplar;
	}

}
