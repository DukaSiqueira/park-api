package br.com.duka.siqueira.parkapi.web.controller;

import br.com.duka.siqueira.parkapi.entity.User;
import br.com.duka.siqueira.parkapi.service.UserService;
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
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.status(CREATED)
                .body(service.create(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(service.findById(id));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok()
                .body(service.updatePassword(id, user.getPassword()));

    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok()
                .body(service.findAll());

    }

}
