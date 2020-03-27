package br.com.compasso.client.domain.model;

import java.time.LocalDate;
import java.time.Period;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;


@Entity
@Data

public class Client{
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@NotNull
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
}
