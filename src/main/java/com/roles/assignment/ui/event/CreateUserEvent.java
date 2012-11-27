package com.roles.assignment.ui.event;

import com.roles.assignment.domain.Person;
import com.roles.assignment.domain.User;

/**
 * User: RubtsovSL
 * Date: 27.11.12
 * Time: 15:14
 */
public class CreateUserEvent {

    private final Person person;

    private final User user;

    public CreateUserEvent(Person person, User user) {
        this.person = person;
        this.user = user;
    }

    public String getUserId() {
        return user.getId();
    }

    public String getPersonId() {
        return person.getId();
    }
}
