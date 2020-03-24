package br.com.compasso.user;

import br.com.compasso.client.ClientApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.compasso.client.domain.model.Client;
import br.com.compasso.client.domain.dto.ClientDTO;
import br.com.compasso.client.service.ClientService;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
class UserServiceTests {

	@Autowired
	private ClientService clientService;
	
	@Test
	public void testeSave() {
		Client client = new Client();
		client.setName("joao");
		client.setBirthday(LocalDate.of(2016, 9, 23));
		client.setGender("M");
		client.setCity("Floripa");
		ClientDTO u = clientService.insert(client);
		
		assertNotNull(u);
		Long id = u.getId();
		assertNotNull(id);
		Optional<ClientDTO> op = clientService.getUserById(id);
		assertTrue(op.isPresent());
		
		u = op.get();
		assertEquals("joao", u.getName());

		clientService.delete(id);
		assertFalse(clientService.getUserById(id).isPresent());
		
	}
	
	@Test
	public void testLista() {
		List<ClientDTO> users = clientService.getUsers();
		assertEquals(6, users.size());
	}


	@Test
	public void testGet() {
		Optional<ClientDTO> op = clientService.getUserById(3L);
		assertTrue(op.isPresent());
		ClientDTO c = op.get();
		assertEquals("João Silva", c.getName());
	}


}
