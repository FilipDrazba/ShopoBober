package pl.edu.pb.wi.authservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pb.wi.authservice.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
