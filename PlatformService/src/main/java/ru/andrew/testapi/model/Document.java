package ru.andrew.testapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "documents")
public class Document {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "filetype")
    private String filetype;

    @Column(name = "file")
    private byte[] file;

    public Document(String filetype, byte[] file) {
        setFiletype(filetype);
        setFile(file);
    }
}
