package br.com.compasso.client.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.compasso.client.domain.model.Client;
import br.com.compasso.client.domain.dto.ClientDTO;
import br.com.compasso.client.service.ClientService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientsController {
	@Autowired
	private ClientService clientService;

	@GetMapping()
	public ResponseEntity<List<ClientDTO>> get() {

		return ResponseEntity.ok(clientService.getUsers());
	}


	@GetMapping("/name")
	public ResponseEntity<List<ClientDTO>> search(@RequestParam("name") String name) {
		List<ClientDTO> clients = clientService.name(name);
		return clients.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(clients);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientDTO> getId(@PathVariable("id") Long id) {
		Optional<ClientDTO> client = clientService.getUserById(id);
		return client.isPresent() ? 
				ResponseEntity.ok(client.get()) :
				ResponseEntity.notFound().build();

	}

	@PostMapping
	public ResponseEntity<Client> post(@Valid @RequestBody Client client) {
			clientService.insert(client);
			URI location = getUri(client.getId());
			return ResponseEntity.created(location).body(client);


	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}


	@PutMapping("/{id}")
	public ResponseEntity<ClientDTO> changeClientName(@PathVariable Long id, @RequestBody String name) throws JsonProcessingException {
		if (name.isEmpty()) return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		ClientDTO clientDTO = clientService.changeClientName(id, name);
		return new ResponseEntity<>(clientDTO, HttpStatus.OK);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		boolean ok = clientService.delete(id);
		return ok ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}

}
