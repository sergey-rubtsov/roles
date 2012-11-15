package com.roles.assignment.domain.document;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * User: RubtsovSL
 * Date: 17.10.12
 * Time: 17:13
 */
@Entity
public class DocumentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String name;

    @OneToMany
    private Set<Signature> authors;

    private Date creationDate;

    //???
    @ManyToOne
    private Document parentDocument;
}
