package br.com.compasso.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRespostaDto {
    private Long id;
    private String name;
    private String gender;
    private String city;
    private LocalDate birthday;
    private int age;
}
