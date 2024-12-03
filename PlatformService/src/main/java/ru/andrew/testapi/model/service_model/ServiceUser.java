package ru.andrew.testapi.model.service_model;

import lombok.*;
import ru.andrew.testapi.model.interfaces.DatabaseDocument;
import ru.andrew.testapi.model.repo_model.Role;

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
