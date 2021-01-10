package com.devsuperior.dsdeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsdeliver.entities.Order;

//Repository - Camada de acesso a dados
//Repository serão os objetos de acessoa a dados
//Padrão utilizado para representar as operações de acesso a dados relacionado a uma entidade
//Objecto repository faz acesso ao banco de dados
//JpaRepository ja faz parte do subframework (Já traz algumas implementacoes padrão tipo CRUD  )
//Com isso terá todas funcionalidades para acesso ao BD com Product
//ProductRepository permite fazer consultas ao banco de uma forma muito fácil.

public interface OrderRepository extends JpaRepository<Order, Long> {

}
