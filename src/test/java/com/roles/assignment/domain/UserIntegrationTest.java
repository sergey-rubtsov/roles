package com.roles.assignment.domain;

import com.roles.assignment.repository.PersonRepository;
import com.roles.assignment.repository.RoleRepository;
import com.roles.assignment.repository.UserRepository;
import com.roles.assignment.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
public class UserIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PersonRepository personRepository;

    @Test
    public void testUserRepositoryNotNull() {
        Assert.assertNotNull(userRepository);
    }

    @Test
    public void testRoleRepositoryNotNull() {
        Assert.assertNotNull(roleRepository);
    }

    @Test
    public void testServiceNotNull() {
        Assert.assertNotNull(userService);
    }

    public static User getUser() {
        User user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setEmail("your@emailhere.com");
        return user;
    }

    public static User getSecondUser() {
        User user = new User();
        user.setLogin("2user");
        user.setPassword("password2");
        user.setEmail("your2@emailhere.com");
        return user;
    }

    @Test
    public void testOneToOne() throws Exception {
        User user = getUser();        
        Person person = getPerson();

        String userId = userService.saveUser(user).getId();
        String personId = personRepository.save(person).getId();

        user = userService.findUser(userId);
        person = personRepository.findOne(personId);
        Assert.assertNotNull(user);
        Assert.assertNotNull(person);

        user.setPerson(person);
        person.setUser(user);

        userId = userService.saveUser(user).getId();
        personId = personRepository.save(person).getId();

        user = userService.findUser(userId);
        person = personRepository.findOne(personId);

        Assert.assertNotNull(user.getPerson().getFirstName());
        Assert.assertEquals(person.getFirstName(), user.getPerson().getFirstName());

        Person foundPerson = personRepository.findOne(person.getId());
        Assert.assertNotNull(foundPerson);
        Assert.assertEquals(getPerson().getFirstName(), foundPerson.getFirstName());

        Assert.assertNotNull(foundPerson.getUser());
        Assert.assertNotNull(user.getLogin());

        Assert.assertEquals(user.getLogin(), foundPerson.getUser().getLogin());
    }

    //@Test
    public void testOneToOneCascade() throws Exception {
        User user = getUser();
        Person person = getPerson();

        String userId = userService.saveUser(user).getId();
        String personId = personRepository.save(person).getId();

        user = userService.findUser(userId);
        person = personRepository.findOne(personId);
        Assert.assertNotNull(user);
        Assert.assertNotNull(person);

        user.setPerson(person);
        userId = userService.updateUser(user).getId();

        user = userService.findUser(userId);
        person = personRepository.findOne(personId);

        Assert.assertNotNull(user.getPerson().getFirstName());
        Assert.assertEquals(person.getFirstName(), user.getPerson().getFirstName());

        Person foundPerson = personRepository.findOne(person.getId());
        Assert.assertNotNull(foundPerson);
        Assert.assertEquals(getPerson().getFirstName(), foundPerson.getFirstName());

        Assert.assertNotNull(foundPerson.getUser());
        Assert.assertNotNull(user.getLogin());

        Assert.assertEquals(user.getLogin(), foundPerson.getUser().getLogin());
    }


/*
    @Test
    public void testGetByUser_Id() throws Exception {
        Person person = data.get(0);
        User user = UserIntegrationTest.getUser();
        Long userId = userRepository.save(user).getId();
        person.setUser(user);
        Long personId = personRepository.save(person).getId();
        person = personRepository.getByUser_Id(userId);
        Assert.assertEquals(personId, person.getId());
    }*/
    
    public Person getPerson() {
        Person person = new Person();
        person.setFirstName("Santa");
        person.setLastName("Klaus");
        person.setEmail("North");
        return person;
    }

    public Person getSecondPerson() {
        Person person = new Person();
        person.setFirstName("Darth");
        person.setLastName("Vader");
        person.setEmail("South");
        return person;
    }

    @Test
    public void testRoles() {
        User user = getUser();
        String id = userService.saveUser(user, getPerson()).getId();
        Assert.assertEquals(0, userService.findUser(id).getRoles().size());
        List roles = new ArrayList<Role>();
        Role roleAdmin = new Role(SystemRoles.ROLE_ADMIN);
        roleAdmin = roleRepository.save(roleAdmin);
        roles.add(roleAdmin);
        user.setRoles(roles);
        id = userService.updateUser(user).getId();
        Assert.assertEquals(1, userService.findUser(id).getRoles().size());
        Assert.assertEquals(SystemRoles.ROLE_ADMIN.roleValue(), userService.findUser(id).getRoles().get(0).getRoleValue());
        Set rolesSet = new HashSet<Role>();
        user = userService.findUser(id);
        rolesSet = user.getRoleSet();
        Assert.assertTrue(rolesSet.contains(roleAdmin.getRoleValue()));
        /*
       Assert.assertEquals(SystemRoles.ROLE_USER, userService.findUser(id).getRoleValue());
       user.setRole(SystemRoles.ROLE_ADMIN);
       id = userService.saveUser(user);
       Assert.assertEquals(SystemRoles.ROLE_ADMIN, userService.findUser(id).getRoleValue());
       */
    }
    
    @Test
    public void testAddRole() {
/*        User user = getUser();
        Role roleAdmin = new Role(SystemRoles.ROLE_ADMIN);
        user.addRole(roleAdmin);
        //roleAdmin.

        Long roleId = roleRepository.save(roleAdmin).getId();
        Long userId = userService.saveUser(user).getId();

        user = userService.findUser(userId);
        roleAdmin = roleRepository.save(roleAdmin);

                user.addRole(roleAdmin);
        userId = userService.saveUser(user).getId();

        User foundUser = userService.findUser(userId);
        Assert.assertEquals(1, userService.findUser(userId).getRoles().size());
        Assert.assertEquals(1, roleRepository.count());*/
    }
}
