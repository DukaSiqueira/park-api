package br.com.duka.siqueira.parkapi.web.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UserCreateDTO {

    private String email;
    private String password;

}
