package com.roles.assignment.domain.document;

import com.roles.assignment.domain.Person;

import javax.persistence.*;
import java.util.Date;

/**
 * User: RubtsovSL
 * Date: 18.10.12
 * Time: 9:16
 */
@Entity
public class Signature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    private Person person;

    private Date date;

    @OneToOne
    private DocumentVersion documentVersion;
}
