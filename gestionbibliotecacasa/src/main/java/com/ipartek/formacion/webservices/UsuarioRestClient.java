package com.ipartek.formacion.webservices;

import java.net.URI;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.ipartek.formacion.dao.persistence.Usuario;


public class UsuarioRestClient {
	
	public static final String REST_SERVICE_URI="http://localhost:8080/gestionbiblioteca/restful/";
	//no ponemos al final de la URI para poder usar la constante en todos los clientes
	
	public static void getAll(){
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>>usuariosMap;
		
		usuariosMap = restTemplate.getForObject(REST_SERVICE_URI+"usuarios/", List.class);
		
		if(usuariosMap!=null){
			for(LinkedHashMap<String, Object> map: usuariosMap){
				Usuario usuario = new Usuario();

				usuario.setId(((Integer) map.get("id")).intValue());
				usuario.setNombre((String) map.get("nombre"));
				usuario.setApellidos((String) map.get("apellidos"));
				usuario.setEmail((String) map.get("email"));
				usuario.setPassword((String) map.get("password"));
				usuario.setfNacimiento((Date)map.get("fNacimiento"));


			}
		}
	}
	
	public Usuario getById(int id){
		Usuario usuario = new Usuario();
		
		RestTemplate restTemplate = new RestTemplate();
		usuario = restTemplate.getForObject(REST_SERVICE_URI+"usuarios/"+id, Usuario.class);
		
		return usuario;
	}
	
	public void create (Usuario usuario){
		
		RestTemplate restTemplate = new RestTemplate();
		URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"usuarios", usuario, Usuario.class);
		
	}
	
	public void update(Usuario usuario){
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(REST_SERVICE_URI+"usuarios"+usuario.getId(), usuario);
	}
	
	public void delete(int id){
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI+"ejemplares"+id, Usuario.class);
	}

}
