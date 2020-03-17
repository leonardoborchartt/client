package br.com.compasso.client.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long>{

	List<Client> findByName(String name);

	
}
