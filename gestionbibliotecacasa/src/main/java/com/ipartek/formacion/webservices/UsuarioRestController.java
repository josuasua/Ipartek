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
import com.ipartek.formacion.dao.persistence.Usuario;
import com.ipartek.formacion.service.interfaces.UsuarioService;

@Controller
@ResponseBody
@RequestMapping(value="/restful/usuarios")
public class UsuarioRestController {
	
	@Autowired
	UsuarioService usu;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Usuario>> getAll(){//responseEntity encapsula el objeto lista al http
		
		List<Usuario> usuarios = usu.getAll();
		ResponseEntity<List<Usuario>> respuesta=null;
		if(usuarios.isEmpty()){
			respuesta = new ResponseEntity<List<Usuario>>(HttpStatus.NOT_FOUND);
		}else{
			respuesta = new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
		}
		
		return respuesta;
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getById(@PathVariable("id") int id){
		
		Usuario usuario = usu.getByID(id);
		ResponseEntity<Usuario> respuesta=null;
		if(usuario==null){
			respuesta = new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}else{
			respuesta = new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
		}
		
		return respuesta;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Usuario usuario){
		ResponseEntity<Void> respuesta=null;
		Usuario usuar = usu.create(usuario);
		if(usuar.getId()>-1){
			respuesta = new ResponseEntity<Void>(HttpStatus.CREATED);
		}else{
			respuesta = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		return respuesta;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") int id){
		ResponseEntity<Void> respuesta=null;
		if(usu.getByID(id)!=null){
			usu.delete(id);
			respuesta = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			respuesta = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		return respuesta;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Usuario> update(@PathVariable("id") int id, @RequestBody Usuario usuario){
		ResponseEntity<Usuario> respuesta=null;
		if(usu.getByID(id)!=null){
			usuario.setId(id);
			usu.update(usuario);
			respuesta = new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
		} else {
			respuesta = new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		
		return respuesta;
	}

}