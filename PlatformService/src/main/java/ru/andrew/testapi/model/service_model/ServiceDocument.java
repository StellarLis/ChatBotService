package ru.andrew.testapi.model.service_model;

import lombok.*;
import ru.andrew.testapi.model.interfaces.DatabaseUser;

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
}
