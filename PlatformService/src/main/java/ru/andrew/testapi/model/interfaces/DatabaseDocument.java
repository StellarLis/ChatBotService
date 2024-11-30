package ru.andrew.testapi.model.interfaces;

public interface DatabaseDocument {
    Long getId();
    void setId(Long id);
    String getFiletype();
    void setFiletype(String filetype);
    byte[] getFile();
    void setFile(byte[] file);
    DatabaseUser getUser();
}
