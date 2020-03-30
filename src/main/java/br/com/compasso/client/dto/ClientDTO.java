package br.com.compasso.client.dto;

import java.time.LocalDate;

import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import lombok.Data;

@Data
@NoArgsConstructor
public class ClientDTO {

	private Long id;
	private String name;
	private String gender;
	private String city;
	private LocalDate birthday;
	private int age;



	public ClientDTO create() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, ClientDTO.class);
	}


}
