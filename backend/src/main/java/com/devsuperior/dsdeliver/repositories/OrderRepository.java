package com.devsuperior.dsdeliver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsdeliver.entities.Order;

//Repository - Camada de acesso a dados
//Repository serão os objetos de acessoa a dados
//Padrão utilizado para representar as operações de acesso a dados relacionado a uma entidade
//Objecto repository faz acesso ao banco de dados
//JpaRepository ja faz parte do subframework (Já traz algumas implementacoes padrão tipo CRUD  )
//Com isso terá todas funcionalidades para acesso ao BD com Product
//ProductRepository permite fazer consultas ao banco de uma forma muito fácil.
//"SELECT DISTINCT obj from Order obj  --> detalhe order é o nome da classe e não da tabela
//JOIN FETCH faz o inner join, foca o banco de dados 1 vez so e tras todo mundo com os registros corrspondentes
//products atributo da classe Order
//obj.status = 0 é o Status pending
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("SELECT DISTINCT obj from Order obj JOIN FETCH obj.products " 
			+ " WHERE obj.status = 0 ORDER BY obj.moment ASC")
	List<Order> findOrdersWithProducts();
}
