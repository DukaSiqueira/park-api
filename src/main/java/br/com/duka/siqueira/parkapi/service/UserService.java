package br.com.duka.siqueira.parkapi.service;

import br.com.duka.siqueira.parkapi.entity.User;
import br.com.duka.siqueira.parkapi.exception.EntityNotFoundException;
import br.com.duka.siqueira.parkapi.exception.PasswordInvalidException;
import br.com.duka.siqueira.parkapi.exception.UserNameUniqueViolationException;
import br.com.duka.siqueira.parkapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;


    @Transactional
    public User create(User user) {

        try {
            return repository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new UserNameUniqueViolationException(
                    String.format("Email {%s} already registered", user.getEmail()));
        }
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("User id {%s} not found", id))
        );
    }

    @Transactional
    public void updatePassword(Long id, String currentPassword, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new PasswordInvalidException("Password confirmation different from new password");
        }


        User user = findById(id);

        if (!user.getPassword().equals(currentPassword)) {
            throw new PasswordInvalidException("Incorrect current password");
        }
        user.setPassword(newPassword);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }
}
