package ru.andrew.AIService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.andrew.AIService.model.repo_model.DocumentSQL;

public interface DocumentRepositorySQL extends JpaRepository<DocumentSQL, Long> {
}
