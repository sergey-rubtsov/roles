package com.roles.assignment.domain.document;

import com.roles.assignment.domain.Person;

import javax.persistence.*;
import java.util.Set;

/**
 * User: RubtsovSL
 * Date: 17.10.12
 * Time: 17:10
 */
@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private Status status;

    @OneToOne
    private DocumentDetails documentDetails;

    @OneToOne
    private DocumentType documentType;

    @OneToMany
    private Set<DocumentVersion> documentVersions;

    @ManyToOne
    private Person registrator;

    @ManyToOne
    private Person executor;

}
