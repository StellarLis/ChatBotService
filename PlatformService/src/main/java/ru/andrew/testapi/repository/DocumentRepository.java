package ru.andrew.testapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.andrew.testapi.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
