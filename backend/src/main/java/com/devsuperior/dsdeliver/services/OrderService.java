package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.repositories.OrderRepository;

//Poderia ser Component
//Classe passa a ser um componente registrado que vai poder ser injetado em outros componentes
//Retorna um DTO = Objeto enxuto somente com os dados que o programador mandar
//DTO não serão gerenciados pelo ORM (Não estão mais conectados com o BD)
//Converte as Entidades para DTO
//DTO OBjeto que carrega os dados referentes a um produtos
//@Autowired Já faz a injecao de dependencia de forma transparente
//list.stream() disponível a partir do java 8 transforma em instrução de alta ordem.no caso programacao funcional
////list.stream().map   : Aplica uma função a todos elementos da lista transformando assim em uma nova lista.
//Vai pegar cada elemento x da lista que e do tipo product e instanciar um productDTO a partir dele 
//list.stream().map(x -> new ProductDTO(x) ); ao final tem que reconverter esse sctream para uma lista novamente

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repositoty;
	
	@Transactional(readOnly = true) //Operação somente de leitura - Isso evita o lock no banco de dados.
	public List<OrderDTO> findAll(){
		List<Order> list = repositoty.findOrdersWithProducts();//Repository retorna entidade
		
		return list.stream().map(x -> new OrderDTO(x) ).collect(Collectors.toList())   ;
	}

}
