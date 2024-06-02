package com.beautyandhome.profumishop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.beautyandhome.profumishop.model.Prodotto;

@Repository
public interface ProdottoRepo extends JpaRepository<Prodotto, Long>{
	//Possibilità di implementare i Query Method
	//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/ (cap 4)
	
	List<Prodotto> findProdottoByNome(String nome);

	List<Prodotto> findProdottoByCategoria(String categoria);


	//long countByLastname(String lastname);
	//List<Person> findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);
	//List<Person> findByLastnameIgnoreCase(String lastname);
	//Operatori possibili: Between, LessThan, GreaterThan
	
}