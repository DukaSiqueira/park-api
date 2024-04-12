package br.com.duka.siqueira.parkapi.web.controller;

import br.com.duka.siqueira.parkapi.entity.User;
import br.com.duka.siqueira.parkapi.service.UserService;
import br.com.duka.siqueira.parkapi.web.dto.UserCreateDTO;
import br.com.duka.siqueira.parkapi.web.dto.UserPasswordDTO;
import br.com.duka.siqueira.parkapi.web.dto.UserResponseDTO;
import br.com.duka.siqueira.parkapi.web.dto.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserCreateDTO createDTO) {
        User user = service.create(UserMapper.toUser(createDTO));
        return ResponseEntity.status(CREATED)
                .body(UserMapper.toDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(UserMapper.toDTO(service.findById(id)));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UserPasswordDTO passwordDTO) {
        service.updatePassword(id,
                passwordDTO.getCurrentPassword(),
                passwordDTO.getNewPassword(),
                passwordDTO.getConfirmPassword());
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity.ok()
                .body(UserMapper.toListDto(service.findAll()));

    }

}
