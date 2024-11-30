package ru.andrew.testapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.andrew.testapi.model.repo_model.Document;

public interface DocumentRepositorySQL extends JpaRepository<Document, Long> {
}
