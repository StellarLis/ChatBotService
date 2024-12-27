package ru.andrew.testapi.repository.repo_service;

import ru.andrew.testapi.model.interfaces.DatabaseDocument;
import ru.andrew.testapi.model.service_model.ServiceDocument;

public interface DocumentService {
    DatabaseDocument save(ServiceDocument document);
}
