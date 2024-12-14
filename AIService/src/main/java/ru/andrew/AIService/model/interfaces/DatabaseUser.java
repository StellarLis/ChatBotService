package ru.andrew.AIService.model.interfaces;

import ru.andrew.AIService.model.repo_model.Role;

import java.util.Set;

public interface DatabaseUser {
    Long getId();
    void setId(Long id);
    String getUsername();
    void setUsername(String username);
    String getPassword();
    void setPassword(String password);
    String getEmail();
    void setEmail(String email);
    Role getRole();
    void setRole(Role role);
    Set<DatabaseDocument> getDocuments();
    void setDocuments(Set<DatabaseDocument> documents);
    String getCompanyName();
    void setCompanyName(String companyName);
}
