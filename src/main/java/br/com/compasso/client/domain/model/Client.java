package br.com.compasso.client.domain.model;



import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Table(name = "client")
@Entity
@Data
public class Client {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@Pattern(regexp = "^[M|F]{1}$", message ="Must be M or F")
	private String gender; 
	private String city; 
	private LocalDate birthday;
	
	public int getAge() {
		return Period.between(getBirthday(), LocalDate.now()).getYears();
	}
}
