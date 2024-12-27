package ru.andrew.AIService.model.interfaces;

import ru.andrew.AIService.model.repo_model.DocumentStatus;

public interface DatabaseDocument {
    Long getId();
    void setId(Long id);
    String getFilename();
    void setFilename(String filename);
    String getFiletype();
    void setFiletype(String filetype);
    byte[] getFile();
    void setFile(byte[] file);
    DatabaseUser getUser();
    void setStatus(DocumentStatus status);
    DocumentStatus getStatus();
}
