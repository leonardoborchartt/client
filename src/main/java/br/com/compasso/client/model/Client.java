package br.com.compasso.client.model;

import java.time.LocalDate;
import java.time.Period;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.compasso.client.dto.ClientRespostaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Client{
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@NotNull
	@NotBlank
	private String name;
	@NotNull
	@Pattern(regexp = "^[M|F]{1}$", message ="Must be M or F")
	private String gender;
	@NotNull
	private String city;
	@NotNull
	private LocalDate birthday;

	public int getAge() {
		return Period.between(getBirthday(), LocalDate.now()).getYears();
	}


	public Client toClient() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, Client.class);
	}

	public ClientRespostaDto toResponseDto() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, ClientRespostaDto.class);
	}


}
