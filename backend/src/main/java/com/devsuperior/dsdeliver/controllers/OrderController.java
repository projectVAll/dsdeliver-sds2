package com.devsuperior.dsdeliver.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.services.OrderService;

//Controlador Rest para trabalhar com produto


@RestController
@RequestMapping(value = "/orders")
public class OrderController {
	//Injetando dependencia para o Service
	
	@Autowired
	private OrderService service;
	
	
	//Criando endpoint para metodo getdo http
	//ResponseEntity<T> encapsula resposta http
	//ResponseEntity.ok  codigo 200. resposta deu certo
	@GetMapping
	public ResponseEntity<List<OrderDTO> > findAll(){
		List<OrderDTO> list =  service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO dto){
		
		//macete -- Quando insere um recurso, o correto é retornar o código 201 (Qaundo um recurso é criado)
		//return ResponseEntity.ok().body(dto);//Poderia ser
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
		
	}
	
	@PutMapping("{id}/delivered") //Adiciona a mais do mapeamento original que no caso e: "/orders"
	public ResponseEntity<OrderDTO> setDelivered(@PathVariable Long id){ // para casar com o parametro d aurl
		OrderDTO dto = service.setDelivered(id);
		
		return ResponseEntity.ok().body(dto);
	}
	

}
