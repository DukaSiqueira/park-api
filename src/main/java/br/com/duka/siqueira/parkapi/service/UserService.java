package br.com.duka.siqueira.parkapi.service;

import br.com.duka.siqueira.parkapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

}
