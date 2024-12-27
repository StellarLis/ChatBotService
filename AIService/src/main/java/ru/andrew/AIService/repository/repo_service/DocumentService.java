package ru.andrew.AIService.repository.repo_service;

import ru.andrew.AIService.model.interfaces.DatabaseDocument;

public interface DocumentService {
    DatabaseDocument getById(Long id);
    void save(DatabaseDocument document);
}
