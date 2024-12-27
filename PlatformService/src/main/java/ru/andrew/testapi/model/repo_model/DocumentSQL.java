package ru.andrew.testapi.model.repo_model;

import jakarta.persistence.*;
import lombok.*;
import ru.andrew.testapi.model.interfaces.DatabaseDocument;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "documents")
public class DocumentSQL implements DatabaseDocument {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "filetype")
    private String filetype;

    @Column(name = "file")
    private byte[] file;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserSQL user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DocumentStatus status;
}
