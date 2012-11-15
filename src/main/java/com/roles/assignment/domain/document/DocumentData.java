package com.roles.assignment.domain.document;

import javax.persistence.*;

/**
 * User: RubtsovSL
 * Date: 17.10.12
 * Time: 17:12
 */
@Entity
public class DocumentData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String blobLink;
}
