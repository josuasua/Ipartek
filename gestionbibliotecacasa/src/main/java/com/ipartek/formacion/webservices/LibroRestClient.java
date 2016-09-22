package com.ipartek.formacion.webservices;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.ipartek.formacion.dao.persistence.Libro;

public class LibroRestClient {
	
	public static final String REST_SERVICE_URI="http://localhost:8080/gestionbiblioteca/restful/";
	//no ponemos al final de la URI para poder usar la constante en todos los clientes
	
	public static void getAll(){
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>>librosMap;
		
		librosMap = restTemplate.getForObject(REST_SERVICE_URI+"libros/", List.class);
		
		if(librosMap!=null){
			for(LinkedHashMap<String, Object> map: librosMap){
				Libro libro = new Libro();

				libro.setId(((Integer) map.get("id")).intValue());
				libro.setTitulo((String) map.get("titulo"));
				libro.setAutor((String) map.get("autor"));
				libro.setIsbn((String) map.get("isbn"));
				//asi con todos
			}
		}
	}

	public Libro getById(int id){
		Libro libro = new Libro();
		
		RestTemplate restTemplate = new RestTemplate();
		libro = restTemplate.getForObject(REST_SERVICE_URI+"libros/"+id, Libro.class);
		
		return libro;
	}
	
	public void create (Libro libro){
		
		RestTemplate restTemplate = new RestTemplate();
		URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"cursos", libro, Libro.class);
		
	}
	
	public void update(Libro libro){
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(REST_SERVICE_URI+"libros"+libro.getId(), libro);
	}
	
	public void delete(int id){
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI+"cursos"+id, Libro.class);
	}
}
