package br.com.compasso.client;
import br.com.compasso.client.model.Client;
import br.com.compasso.client.repository.ClientRepository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import br.com.compasso.client.service.ClientService;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTests {


	@Mock
	private ClientRepository clientRepository;

	@InjectMocks
	private ClientService clientService;


	@Test
	public void testaRetornoNomeCliente() {

		String name = "Leonardo";
		Client client = new Client(1L, "Leonardo" , "M", "Floripa", LocalDate.of(1993,9,10));
		List<Client> list = new ArrayList<>();
		list.add(client.toClient());

		when(clientRepository.findByName(name)).
				thenReturn(list);

		Assertions.assertThat(clientService.name(name)).hasSize(1);
		Assertions.assertThat(clientService.name(name).get(0).getName()).isEqualTo(name);
	}
	@Test
	public void testaRetornoDataNascimentoCliente() {
		String dataNascimento = "1993-09-10";
		Client client = new Client(1L, "Leonardo" , "M", "Floripa", LocalDate.of(1993,9,10));
		List<Client> list = new ArrayList<>();
		list.add(client.toClient());

		when(clientRepository.findByName(dataNascimento)).
				thenReturn(list);

		Assertions.assertThat(clientService.name(dataNascimento)).hasSize(1);
		Assertions.assertThat(clientService.name(dataNascimento).get(0).getBirthday()).isEqualTo(dataNascimento);
		Assertions.assertThat(clientService.name(dataNascimento).get(0).getAge()).isEqualTo(26);

	}




}
