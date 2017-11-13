package com.pg2.simbweb.client;

import java.net.URI;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pg2.simbweb.domain.bovino.Peso;




@Component
public class PesoClient {
	
	private RestTemplate restTemplate = new RestTemplate();
	private String URI_BASE = "http://127.0.0.1:8080/peso";
	
	
	public String deletar(Long codigo){
		
		RequestEntity<Void> request = RequestEntity
				.delete(URI.create(URI_BASE + "/" + codigo))
				.build();
		
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
		
		return response.getStatusCode().toString();
	}
	
	public String alterar(Peso peso) {
		
		RequestEntity<Peso> request = RequestEntity
				.put(URI.create(URI_BASE))
				.body(peso);
		
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
		
		return response.getHeaders().getLocation().toString();
		
	}
	
public Peso listarUm(Long codigo){
		
		RequestEntity<Void> request = RequestEntity
				.get(URI.create(URI_BASE+"/"+codigo))
				.build();
		ResponseEntity<Peso> response = restTemplate.exchange(request, Peso.class);
		return response.getBody();
			
	}

}
