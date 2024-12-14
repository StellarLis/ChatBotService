package ru.andrew.AIService.repository.repo_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.andrew.AIService.model.interfaces.DatabaseDocument;
import ru.andrew.AIService.repository.DocumentRepositorySQL;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DocumentServiceSQL implements DocumentService {
    private final DocumentRepositorySQL documentRepository;

    @Override
    public DatabaseDocument getById(Long id) throws NoSuchElementException {
        return documentRepository.findById(id).orElseThrow();
    }
}
