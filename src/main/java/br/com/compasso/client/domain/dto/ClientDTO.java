package br.com.compasso.client.domain.dto;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;

import br.com.compasso.client.domain.model.Client;
import lombok.Data;

@Data
public class ClientDTO {

	private Long id;
	private String name;
	private String gender;
	private String city;
	private LocalDate birthday;
	private int age;

	
	public static ClientDTO create(Client client) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(client, ClientDTO.class);
	}

}
