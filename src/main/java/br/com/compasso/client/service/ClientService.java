package br.com.compasso.client.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.compasso.client.dto.ClientRespostaDto;
import br.com.compasso.client.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import br.com.compasso.client.model.Client;
import br.com.compasso.client.dto.ClientDTO;


@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public List<Client> getUsers() {
		return clientRepository.findAll().stream().map(Client::toClient).collect(Collectors.toList());
	}

	public Optional<Client> getUserById(Long id) {
		return clientRepository.findById(id);
	}

	public List<Client> name(String name) {
		return clientRepository.findByName(name);
	}
	
	public ClientRespostaDto insert(Client client) {
		Assert.isNull(client.getId(), "não foi possivel atualizar o registro");
		Client city = client.toClient();
		Client savedCity = clientRepository.save(city);
		return savedCity.toResponseDto();
	}


	public boolean delete(Long id) {
		if (getUserById(id).isPresent()) {
			clientRepository.deleteById(id);
			return true;
		}
		return false;
	}


	public Client changeClientName(Long id, String name) throws Exception {

		ObjectMapper mapper= new ObjectMapper();
		ClientDTO mp = mapper.readValue(name, ClientDTO.class);

		Optional<Client> optionalClient = clientRepository.findById(id);
		if (!optionalClient.isPresent()) throw new Exception();
		Client client = optionalClient.get();
		client.setName(mp.getName());
			Client savedCity = clientRepository.save(client);
		return savedCity.toClient();

	}
}
