package com.ipartek.formacion.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipartek.formacion.dao.persistence.Libro;
import com.ipartek.formacion.service.interfaces.LibroService;

@Controller
@ResponseBody
@RequestMapping(value="/restful/libros")
public class LibroRestController {
	
	@Autowired
	LibroService lib;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Libro>> getAll(){//responseEntity encapsula el objeto lista al http
		
		List<Libro> libros = lib.getAll();
		ResponseEntity<List<Libro>> respuesta=null;
		if(libros.isEmpty()){
			respuesta = new ResponseEntity<List<Libro>>(HttpStatus.NOT_FOUND);
		}else{
			respuesta = new ResponseEntity<List<Libro>>(libros,HttpStatus.OK);
		}
		
		return respuesta;
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> getById(@PathVariable("id") int id){
		
		Libro libro = lib.getByID(id);
		ResponseEntity<Libro> respuesta=null;
		if(libro==null){
			respuesta = new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);
		}else{
			respuesta = new ResponseEntity<Libro>(libro,HttpStatus.OK);
		}
		
		return respuesta;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Libro libro){
		ResponseEntity<Void> respuesta=null;
		Libro libr = lib.create(libro);
		if(libr.getId()>-1){
			respuesta = new ResponseEntity<Void>(HttpStatus.CREATED);
		}else{
			respuesta = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		return respuesta;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") int id){
		ResponseEntity<Void> respuesta=null;
		if(lib.getByID(id)!=null){
			lib.delete(id);
			respuesta = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			respuesta = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		return respuesta;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Libro> update(@PathVariable("id") int id, @RequestBody Libro libro){
		ResponseEntity<Libro> respuesta=null;
		if(lib.getByID(id)!=null){
			libro.setId(id);
			lib.update(libro);
			respuesta = new ResponseEntity<Libro>(libro,HttpStatus.OK);
		} else {
			respuesta = new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);
		}
		
		return respuesta;
	}
}
