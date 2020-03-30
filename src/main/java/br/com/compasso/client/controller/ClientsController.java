package br.com.compasso.client.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import br.com.compasso.client.model.Client;
import br.com.compasso.client.service.ClientService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientsController {
	@Autowired
	private ClientService clientService;


	@GetMapping("/name")
	public ResponseEntity<List<Client>> search(@RequestParam("name") String name) {
		List<Client> clients = clientService.name(name);
		return clients.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(clients);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> getId(@PathVariable("id") Long id) {
		Optional<Client> client = clientService.getUserById(id);
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
	public ResponseEntity<Client> changeClientName(@Valid @PathVariable Long id, @RequestBody String name) throws Exception {
		try {
			if (name.isEmpty()|| !(clientService.getUserById(id).isPresent()))
				return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Client client = clientService.changeClientName(id, name);
		return new ResponseEntity<>(client, HttpStatus.OK);


	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Client> delete(@PathVariable("id") Long id) {
		boolean ok = clientService.delete(id);
		return ok ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}

}
