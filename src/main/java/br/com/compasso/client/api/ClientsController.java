package br.com.compasso.client.api;

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

import br.com.compasso.client.domain.Client;
import br.com.compasso.client.domain.dto.ClientDTO;
import br.com.compasso.client.service.ClientService;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientsController {
	@Autowired
	private ClientService service;

	@GetMapping()
	public ResponseEntity<List<ClientDTO>> get() {

		return ResponseEntity.ok(service.getUsers());
	}

	@GetMapping("/name")
	public ResponseEntity<?> search(@RequestParam("name") String name) {
		List<ClientDTO> clients = service.name(name);
		return clients.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(clients);
	}

	@GetMapping("/{id}")
	public ResponseEntity getId(@PathVariable("id") Long id) {
		Optional<ClientDTO> client = service.getUserById(id);
		return client.isPresent() ? 
				ResponseEntity.ok(client.get()) :
				ResponseEntity.notFound().build();

	}

	@PostMapping
	public ResponseEntity post(@RequestBody Client client) {
		try {
			ClientDTO u = service.insert(client);
			URI location = getUri(u.getId());
			return ResponseEntity.created(location).build();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@PutMapping("/{id}")
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Client client) {
//		try {
		client.setId(id);
		ClientDTO u = service.update(client, id);
		return u != null ? ResponseEntity.ok(u) : ResponseEntity.notFound().build();

//		} catch (Exception e) {
//			 return ResponseEntity.badRequest().build();
//		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		boolean ok = service.delete(id);
		return ok ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}

}
