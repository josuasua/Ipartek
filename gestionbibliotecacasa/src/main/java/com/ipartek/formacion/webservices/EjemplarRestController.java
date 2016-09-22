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

import com.ipartek.formacion.dao.persistence.Ejemplar;
import com.ipartek.formacion.service.interfaces.EjemplarService;

@Controller
@ResponseBody
@RequestMapping(value="/restful/ejemplares")
public class EjemplarRestController {
	
	@Autowired
	EjemplarService ejem;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Ejemplar>> getAll(){//responseEntity encapsula el objeto lista al http
		
		List<Ejemplar> ejemplares = ejem.getAll();
		ResponseEntity<List<Ejemplar>> respuesta=null;
		if(ejemplares.isEmpty()){
			respuesta = new ResponseEntity<List<Ejemplar>>(HttpStatus.NOT_FOUND);
		}else{
			respuesta = new ResponseEntity<List<Ejemplar>>(ejemplares,HttpStatus.OK);
		}
		
		return respuesta;
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ejemplar> getById(@PathVariable("id") int id){
		
		Ejemplar ejemplar = ejem.getByID(id);
		ResponseEntity<Ejemplar> respuesta=null;
		if(ejemplar==null){
			respuesta = new ResponseEntity<Ejemplar>(HttpStatus.NOT_FOUND);
		}else{
			respuesta = new ResponseEntity<Ejemplar>(ejemplar,HttpStatus.OK);
		}
		
		return respuesta;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Ejemplar ejemplar){
		ResponseEntity<Void> respuesta=null;
		Ejemplar ejemp = ejem.create(ejemplar);
		if(ejemp.getId()>-1){
			respuesta = new ResponseEntity<Void>(HttpStatus.CREATED);
		}else{
			respuesta = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		return respuesta;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") int id){
		ResponseEntity<Void> respuesta=null;
		if(ejem.getByID(id)!=null){
			ejem.delete(id);
			respuesta = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			respuesta = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		return respuesta;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Ejemplar> update(@PathVariable("id") int id, @RequestBody Ejemplar ejemplar){
		ResponseEntity<Ejemplar> respuesta=null;
		if(ejem.getByID(id)!=null){
			ejemplar.setId(id);
			ejem.update(ejemplar);
			respuesta = new ResponseEntity<Ejemplar>(ejemplar,HttpStatus.OK);
		} else {
			respuesta = new ResponseEntity<Ejemplar>(HttpStatus.NOT_FOUND);
		}
		
		return respuesta;
	}
}