package com.roles.assignment.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User: RubtsovSL
 * Date: 11.10.12
 * Time: 15:03
 */

@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String id;

    @Column(unique=true, nullable=false)
    @Size(max = 32)
    private String role;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(SystemRoles role) {
        this.role = role.roleValue();
    }

    @ManyToMany(mappedBy="roles")
    private List<User> users = new ArrayList<User>();

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleValue() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

