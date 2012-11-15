package com.roles.assignment.service;

import com.roles.assignment.domain.Person;
import com.roles.assignment.domain.Role;
import com.roles.assignment.domain.SystemRoles;
import com.roles.assignment.domain.User;
import com.roles.assignment.repository.PersonRepository;
import com.roles.assignment.repository.RoleRepository;
import com.roles.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: RubtsovSL
 * Date: 04.10.12
 * Time: 8:32
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public long countAllUsers() {
        return userRepository.count();
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.delete(id);
    }

    @Override
    public User findUser(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByLoginLike(String login) {
        return userRepository.findByLoginLike(login);
    }

/*    @Override
    public User addRole(Long userId, Role role) {
        User user = userRepository.findOne(userId);
        role = roleRepository.save(role);
        user.addRole(role);
        return userRepository.save(user);
    }*/

    @Override
    public User addRole(String userId, Role role) {
        return addNewRole(userRepository.findOne(userId), role);
    }

    @Override
    public User addRole(User user, Role role) {
        return addNewRole(user, role);
    }
    
    private User addNewRole(User user, Role role) {
        Role newRole;
        if (role.getId() == null) {
            newRole = roleRepository.findByRoleLike(role.getRoleValue());
        } else {
            newRole = roleRepository.findOne(role.getId());
        }        
        if (newRole == null) {
            newRole = roleRepository.save(role);
        }
        user.addRole(newRole);
        return userRepository.save(user);
    }

    @Override
    public User addSystemAdminRole(User user) {
        user = addNewRole(user, new Role(SystemRoles.ROLE_ADMIN));
        return userRepository.save(user);
    }

    @Override
    public User addSystemUserRole(User user) {
        user = addNewRole(user, new Role(SystemRoles.ROLE_USER));
        return userRepository.save(user);
    }

/*    @Override
    public List<User> findByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }*/

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User saveUser(User user, Person userDetails) {
        user.setPerson(userDetails);
        userDetails = personRepository.save(userDetails);
        //user.setPerson(userDetails);
        return userRepository.save(user);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
