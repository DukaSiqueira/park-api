package br.com.duka.siqueira.parkapi.web.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UserResponseDTO {

    private Long id;
    private String email;
    private String role;

}
