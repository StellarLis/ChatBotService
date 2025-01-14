package ru.andrew.testapi.repository.repo_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.andrew.testapi.model.interfaces.DatabaseDocument;
import ru.andrew.testapi.model.repo_model.DocumentSQL;
import ru.andrew.testapi.model.repo_model.UserSQL;
import ru.andrew.testapi.model.service_model.ServiceDocument;
import ru.andrew.testapi.repository.DocumentRepositorySQL;

@Service
@RequiredArgsConstructor
public class DocumentServiceSQL implements DocumentService {
    private final DocumentRepositorySQL documentRepository;

    @Override
    public DatabaseDocument save(ServiceDocument document) {
        DocumentSQL dbDocument = DocumentSQL.builder()
                .filename(document.getFilename())
                .filetype(document.getFiletype())
                .file(document.getFile())
                .user((UserSQL) document.getUser())
                .status(document.getStatus()).build();
        return documentRepository.save(dbDocument);
    }
}
