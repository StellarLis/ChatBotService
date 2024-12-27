package ru.andrew.AIService.model.service_model;

import lombok.*;
import ru.andrew.AIService.model.interfaces.DatabaseUser;
import ru.andrew.AIService.model.repo_model.DocumentStatus;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDocument {
    private Long id;
    private String filetype;
    private byte[] file;
    private DatabaseUser user;
    private DocumentStatus status;
}
