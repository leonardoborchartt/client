package br.com.compasso.client.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import br.com.compasso.client.repository.ClientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import br.com.compasso.client.domain.model.Client;
import br.com.compasso.client.domain.dto.ClientDTO;


@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public List<ClientDTO> getUsers() {
		return clientRepository.findAll().stream().map(ClientDTO::create).collect(Collectors.toList());
	}

	public Optional<ClientDTO> getUserById(Long id) {
		return clientRepository.findById(id).map(ClientDTO::create);
	}

	public List<ClientDTO> name(String name) {
		return clientRepository.findByName(name).stream().map(ClientDTO::create).collect(Collectors.toList());
	}
	
	public ClientDTO insert(Client client) {
		Assert.isNull(client.getId(), "não foi possivel atualizar o registro");
		return ClientDTO.create(clientRepository.save(client));
	}

	public ClientDTO update(Client client, Long id) {
		Assert.notNull(id, "Não foi possivel atualizar");

		Optional<Client> optional = clientRepository.findById(id);
		if (optional.isPresent()) {
			Client db = optional.get();
			db.setName(client.getName());
			db.setGender(client.getGender());
			db.setBirthday(client.getBirthday());
			db.setCity(client.getCity());
			clientRepository.save(db);
			return ClientDTO.create(db);
		} else {
			throw new RuntimeException("Não foi possivel registro");
		}

	}

	public boolean delete(Long id) {
		if (getUserById(id).isPresent()) {
			clientRepository.deleteById(id);
			return true;
		}
		return false;
	}


	public ClientDTO changeClientName(Long id, String name) throws JsonProcessingException {

		ObjectMapper mapper= new ObjectMapper();
		ClientDTO mp = mapper.readValue(name, ClientDTO.class);



		Optional<Client> optionalClient = clientRepository.findById(id);
		if(optionalClient.isPresent()){
		Client client = optionalClient.get();
		client.setName(mp.getName());
		clientRepository.save(client);
		return ClientDTO.create(client);
		}
		return ClientDTO.create(null);
	}
}
