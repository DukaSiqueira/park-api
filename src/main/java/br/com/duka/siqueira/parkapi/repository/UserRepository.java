package br.com.duka.siqueira.parkapi.repository;

import br.com.duka.siqueira.parkapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
