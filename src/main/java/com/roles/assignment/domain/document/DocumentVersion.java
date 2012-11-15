package com.roles.assignment.domain.document;

import javax.persistence.*;

/**
 * User: RubtsovSL
 * Date: 18.10.12
 * Time: 9:13
 */
@Entity
public class DocumentVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    private DocumentDetails documentDetails;

    @OneToOne
    private DocumentData documentData;
}
