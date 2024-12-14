package ru.andrew.AIService.model.repo_model;

import jakarta.persistence.*;
import lombok.*;
import ru.andrew.AIService.model.interfaces.DatabaseDocument;
import ru.andrew.AIService.model.interfaces.DatabaseUser;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserSQL implements DatabaseUser {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<DocumentSQL> documents;

    @Column(name = "company_name")
    private String companyName;

    public Set<DatabaseDocument> getDocuments() {
        return new HashSet<>(documents);
    }

    public void setDocuments(Set<DatabaseDocument> documents) {
        HashSet<DocumentSQL> result = new HashSet<>();
        for (DatabaseDocument document : documents) {
            result.add((DocumentSQL) document);
        }
        this.documents = result;
    }
}
