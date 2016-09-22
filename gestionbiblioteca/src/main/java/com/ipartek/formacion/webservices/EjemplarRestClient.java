package com.ipartek.formacion.webservices;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.ipartek.formacion.dao.persistence.Ejemplar;

public class EjemplarRestClient {
	
	public static final String REST_SERVICE_URI="http://localhost:8080/gestionbiblioteca/restful/";
	//no ponemos al final de la URI para poder usar la constante en todos los clientes
	
	public static void getAll(){
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>>ejemplaresMap;
		
		ejemplaresMap = restTemplate.getForObject(REST_SERVICE_URI+"ejemplares/", List.class);
		
		if(ejemplaresMap!=null){
			for(LinkedHashMap<String, Object> map: ejemplaresMap){
				Ejemplar ejemplar = new Ejemplar();

				ejemplar.setId(((Integer) map.get("id")).intValue());
				ejemplar.setEditorial((String) map.get("editorial"));
				ejemplar.setNumeropaginas(((Integer) map.get("numeropaginas")).intValue());

			}
		}
	}
	
	public Ejemplar getById(int id){
		Ejemplar ejemplar = new Ejemplar();
		
		RestTemplate restTemplate = new RestTemplate();
		ejemplar = restTemplate.getForObject(REST_SERVICE_URI+"libros/"+id, Ejemplar.class);
		
		return ejemplar;
	}
	
	public void create (Ejemplar ejemplar){
		
		RestTemplate restTemplate = new RestTemplate();
		URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"ejemplares", ejemplar, Ejemplar.class);
		
	}
	
	public void update(Ejemplar ejemplar){
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(REST_SERVICE_URI+"ejemplares"+ejemplar.getId(), ejemplar);
	}
	
	public void delete(int id){
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI+"ejemplares"+id, Ejemplar.class);
	}

}
