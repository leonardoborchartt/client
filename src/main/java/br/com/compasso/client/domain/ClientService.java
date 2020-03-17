package br.com.compasso.client.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.compasso.client.domain.dto.ClientDTO;

@Service
public class ClientService {

	@Autowired
	private ClientRepository rep;

	public List<ClientDTO> getUsers() {
		return rep.findAll().stream().map(ClientDTO::create).collect(Collectors.toList());
	}

	public Optional<ClientDTO> getUserById(Long id) {
		return rep.findById(id).map(ClientDTO::create);
	}

	public List<Client> getUserByName(String name) {
		return rep.findByName(name);
		
	}

	public List<ClientDTO> name(String name) {
		return rep.findByName(name).stream().map(ClientDTO::create).collect(Collectors.toList());
	}
	
	public ClientDTO insert(Client client) {
		Assert.isNull(client.getId(), "não foi possivel atualizar o registro");
		return ClientDTO.create(rep.save(client));
	}

	public ClientDTO update(Client client, Long id) {
		Assert.notNull(id, "Não foi possivel atualizar");

		Optional<Client> optional = rep.findById(id);
		if (optional.isPresent()) {
			Client db = optional.get();
			db.setName(client.getName());
			db.setGender(client.getGender());
			db.setBirthday(client.getBirthday());
			db.setCity(client.getCity());

			System.out.println("Client id " + db.getId());
			rep.save(db);
			return ClientDTO.create(db);
		} else {
			throw new RuntimeException("Não foi possivel registro");
		}

//		getUserById(id).map(db-> {
//			
//			db.setNome(user.getNome());
//			db.setEmail(user.getEmail());
//			db.setLogin(user.getLogin());
//			System.out.println("Client id " + db.getId());
//			rep.save(db);
//			return db
//		}).orElseThrow((-> new RuntimeException("dasd")));
	}

	public boolean delete(Long id) {
		if (getUserById(id).isPresent()) {
			rep.deleteById(id);
			return true;
		}
		return false;
	}


}
