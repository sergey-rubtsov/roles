package com.roles.assignment.ui.entity;

import com.roles.assignment.domain.Person;
import com.roles.assignment.domain.User;

import java.io.Serializable;
import java.util.Date;

/**
 * User: RubtsovSL
 * Date: 04.12.12
 * Time: 11:05
 */
public class SummaryUserData implements Serializable {

    private String firstName = "";

    private String lastName = "";

    private Date birthdate;

    private String phoneNumber = "";

    private String email = "";

    private String login;

    private String password;

    public SummaryUserData() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public User getUser() {
        User user = new User();
        user.setLogin(getLogin());
        user.setPassword(getPassword());
        user.setEmail(getEmail());
        return user;
    }
    
    public Person getPerson() {
        Person person = new Person();
        person.setFirstName(getFirstName());
        person.setLastName(getLastName());
        person.setPhoneNumber(getPhoneNumber());
        person.setBirthdate(getBirthdate());
        return person;
    }
}