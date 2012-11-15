package com.roles.assignment.service;

import com.roles.assignment.domain.Person;
import com.roles.assignment.domain.Role;
import com.roles.assignment.domain.User;

import java.util.List;

public interface UserService {

	public abstract long countAllUsers();

	public abstract void deleteUser(User user);

    public abstract void deleteUser(String id);

	public abstract User findUser(String id);

    public User findByLoginLike(String login);
    
    public User addRole(String userId, Role role);

    public User addRole(User user, Role role);

    public User addSystemAdminRole(User user);

    public User addSystemUserRole(User user);

    //public List<User> findByLastName(String lastName);

    //public List<User> findByFirstName(String firstName);

	public abstract List<User> findAllUsers();

	public abstract User saveUser(User user, Person userDetails);

    public abstract User saveUser(User user);

	public abstract User updateUser(User user);

}
