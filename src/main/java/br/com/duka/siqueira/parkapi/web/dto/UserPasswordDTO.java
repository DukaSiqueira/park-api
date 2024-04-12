package br.com.duka.siqueira.parkapi.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UserPasswordDTO {

    @NotBlank(message = "Current password cannot be null")
    @Size(min = 6, max = 6, message = "The current password must be 6 characters long")
    private String currentPassword;

    @NotBlank(message = "New password cannot be null")
    @Size(min = 6, max = 6, message = "The new password must be 6 characters long")
    private String newPassword;

    @NotBlank(message = "Confirm password cannot be null")
    @Size(min = 6, max = 6, message = "The confirm password must be 6 characters long")
    private String confirmPassword;

}
