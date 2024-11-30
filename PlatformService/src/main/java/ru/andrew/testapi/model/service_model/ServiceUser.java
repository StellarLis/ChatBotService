package ru.andrew.testapi.model.service_model;

import lombok.*;
import ru.andrew.testapi.model.repo_model.Role;

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
}
