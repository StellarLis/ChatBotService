package ru.andrew.AIService.model.service_model;

import lombok.*;
import ru.andrew.AIService.model.interfaces.DatabaseDocument;
import ru.andrew.AIService.model.repo_model.Role;

import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceUser {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Role role;
    private Set<DatabaseDocument> documents;
    private String companyName;
}
