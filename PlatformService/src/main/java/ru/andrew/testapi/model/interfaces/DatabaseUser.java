package ru.andrew.testapi.model.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import ru.andrew.testapi.model.repo_model.Role;

import java.util.Set;

public interface DatabaseUser extends UserDetails {
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
}
