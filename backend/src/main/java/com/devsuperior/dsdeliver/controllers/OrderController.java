package com.devsuperior.dsdeliver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	

}
