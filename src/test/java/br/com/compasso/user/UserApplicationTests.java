package br.com.compasso.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.compasso.client.domain.Client;
import br.com.compasso.client.domain.ClientService;
import br.com.compasso.client.domain.dto.ClientDTO;

import static junit.framework.TestCase.*;

import java.util.List;
import java.util.Optional;


@SpringBootTest
class UserApplicationTests {

	@Autowired
	private ClientService service;
	
	@Test
	public void testeSave() {
		Client client = new Client();
		client.setName("joao");
		client.setEmail("joao@hotmail.com");
		client.setUsername("jo");
		ClientDTO u = service.insert(client);
		
		assertNotNull(u);
		Long id = u.getId();
		assertNotNull(id);
		//buscar o objeto
		Optional<ClientDTO> op = service.getUserById(id);
		assertTrue(op.isPresent());
		
		u = op.get();
		assertEquals("joao", u.getName());
		assertEquals("joao@hotmail.com", u.getEmail());
		assertEquals("jo", u.getUsername());
		
		//deleta o usuario
		service.delete(id);
		
		///verifica se deletou
		assertFalse(service.getUserById(id).isPresent());		
		
	}
	
	@Test
	public void testLista() {
		List<ClientDTO> users = service.getUsers();
		assertEquals(7, users.size());
	}
	
	
	@Test
	public void testGet() {
		Optional<ClientDTO> op = service.getUserById(3L);
		assertTrue(op.isPresent());
		ClientDTO c = op.get();
		assertEquals("Client", c.getName());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 
}
