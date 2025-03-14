package ru.andrew.testapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.andrew.testapi.model.repo_model.UserSQL;

import java.util.Optional;

public interface UserRepositorySQL extends JpaRepository<UserSQL, Long> {
    Optional<UserSQL> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
