package br.com.duka.siqueira.parkapi.web.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UserCreateDTO {

    @Email(message = "Invalid email format", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    @NotBlank(message = "Email cannot be null")
    private String email;

    @NotBlank(message = "Password cannot be null")
    @Size(min = 6, max = 6, message = "The password must be 6 characters long")
    private String password;

}
