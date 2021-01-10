package com.devsuperior.dsdeliver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.services.ProductService;

//Controlador Rest para trabalhar com produto


@RestController
@RequestMapping(value = "/products")
public class ProductController {
	//Injetando dependencia para o Service
	
	@Autowired
	private ProductService service;
	
	
	//Criando endpoint para metodo getdo http
	//ResponseEntity<T> encapsula resposta http
	//ResponseEntity.ok  codigo 200. resposta deu certo
	@GetMapping
	public ResponseEntity<List<ProductDTO> > findAll(){
		List<ProductDTO> list =  service.findAll();
		return ResponseEntity.ok().body(list);
	}
	

}
