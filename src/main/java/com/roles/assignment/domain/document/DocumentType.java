package com.roles.assignment.domain.document;

import javax.persistence.*;

/**
 * User: RubtsovSL
 * Date: 18.10.12
 * Time: 10:08
 */
@Entity
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String type;
}
