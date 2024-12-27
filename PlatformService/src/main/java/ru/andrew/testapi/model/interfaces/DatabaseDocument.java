package ru.andrew.testapi.model.interfaces;

import ru.andrew.testapi.model.repo_model.DocumentStatus;

public interface DatabaseDocument {
    Long getId();
    void setId(Long id);
    void setFilename(String filename);
    String getFilename();
    String getFiletype();
    void setFiletype(String filetype);
    byte[] getFile();
    void setFile(byte[] file);
    DatabaseUser getUser();
    void setStatus(DocumentStatus status);
    DocumentStatus getStatus();
}
