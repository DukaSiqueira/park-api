package br.com.duka.siqueira.parkapi.service;

import br.com.duka.siqueira.parkapi.entity.User;
import br.com.duka.siqueira.parkapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    @Transactional
    public User create(User user) {
        return repository.save(user);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
    }
}
