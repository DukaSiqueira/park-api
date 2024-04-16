package br.com.duka.siqueira.parkapi.web.controller;

import br.com.duka.siqueira.parkapi.entity.User;
import br.com.duka.siqueira.parkapi.service.UserService;
import br.com.duka.siqueira.parkapi.web.dto.UserCreateDTO;
import br.com.duka.siqueira.parkapi.web.dto.UserPasswordDTO;
import br.com.duka.siqueira.parkapi.web.dto.UserResponseDTO;
import br.com.duka.siqueira.parkapi.web.dto.mapper.UserMapper;
import br.com.duka.siqueira.parkapi.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Tag(name = "Users",
        description = "Contains all the operations relating to users, such as register, list all, search, update.")
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserService service;

    @Operation(summary = "Create new user",
    description = "Resource for create new user",
    responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Resource successfully created",
                    content =
                        @Content(mediaType = "Application/json",
                                schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email already registered",
                    content =
                    @Content(mediaType = "Application/json",
                            schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(
                    responseCode = "422",
                    description = "Invalid input data",
                    content =
                    @Content(mediaType = "Application/json",
                            schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserCreateDTO createDTO) {
        User user = service.create(UserMapper.toUser(createDTO));
        return ResponseEntity.status(CREATED)
                .body(UserMapper.toDTO(user));
    }

    @Operation(summary = "Find user by Id",
            description = "Resource for find user by Id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Resource successfully recovered",
                            content =
                            @Content(mediaType = "Application/json",
                                    schema = @Schema(implementation = UserResponseDTO.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Resource not found",
                            content =
                            @Content(mediaType = "Application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))),
            })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(UserMapper.toDTO(service.findById(id)));

    }

    @Operation(summary = "Update password",
            description = "Resource for update password",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Password update successfully",
                            content =
                            @Content(mediaType = "Application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Resource not found",
                            content =
                            @Content(mediaType = "Application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid arguments",
                            content =
                            @Content(mediaType = "Application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Invalid input data",
                            content =
                            @Content(mediaType = "Application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id,@Valid @RequestBody UserPasswordDTO passwordDTO) {
        service.updatePassword(id,
                passwordDTO.getCurrentPassword(),
                passwordDTO.getNewPassword(),
                passwordDTO.getConfirmPassword());
        return ResponseEntity.noContent().build();

    }

    @Operation(summary = "Find all users",
            description = "Resource for recovered all users",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Users found",
                            content =
                            @Content(mediaType = "Application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserResponseDTO.class)))),
            })
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity.ok()
                .body(UserMapper.toListDto(service.findAll()));

    }

}
