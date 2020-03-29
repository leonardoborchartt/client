package br.com.compasso.client;
import br.com.compasso.client.domain.model.Client;
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
		String name = "leo";
		Client client = new Client(1L, "leo" , "M", "Floripa", LocalDate.of(1933,9,10));
		List<Client> list = new ArrayList<>();
		list.add(client.toClient());

		when(clientRepository.findByName(name)).
				thenReturn(list);

		Assertions.assertThat(clientService.name(name)).hasSize(1);
		Assertions.assertThat(clientService.name(name).get(0).getName()).isEqualTo(name);
	}



}
