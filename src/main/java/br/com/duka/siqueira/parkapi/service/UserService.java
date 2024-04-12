package br.com.duka.siqueira.parkapi.service;

import br.com.duka.siqueira.parkapi.entity.User;
import br.com.duka.siqueira.parkapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public User updatePassword(Long id, String currentPassword, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException("password confirmation different from new password");
        }


        User user = findById(id);

        if (!user.getPassword().equals(currentPassword)) {
            throw new RuntimeException("Incorrect current password");
        }
        user.setPassword(newPassword);
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }
}
