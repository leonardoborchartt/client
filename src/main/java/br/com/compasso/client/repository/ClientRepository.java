package br.com.compasso.client.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.compasso.client.model.Client;


public interface ClientRepository extends JpaRepository<Client, Long>{


	List<Client> findByName(String name);


}
