package com.roles.assignment.domain;

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

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
public class UserSimpleIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testUserRepositoryNotNull() {
        Assert.assertNotNull(userRepository);
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
    public void testCountAllUsers() {
        userService.saveUser(getUser());
        userService.saveUser(getSecondUser());
        long count = userService.countAllUsers();
        Assert.assertTrue("Counter for 'User' incorrectly reported there were no entries", count == 2);
    }

    @Test
    public void testFindByLogin() {
        userService.saveUser(getUser());
        userService.saveUser(getSecondUser());
        Assert.assertNull(userService.findByLoginLike("no exist"));
        Assert.assertEquals("2user", userService.findByLoginLike("2user").getLogin());
    }

    @Test
    public void testDelete() throws Exception {
        User user1 = getUser();
        User user2 = getSecondUser();
        userService.saveUser(user1);
        userService.saveUser(user2);
        long count = userService.countAllUsers();
        Assert.assertTrue(count == 2);
        userService.deleteUser(user1);
        count = userService.countAllUsers();
        Assert.assertTrue(count == 1);
        userService.saveUser(user1);
        userService.deleteUser(user2.getId());
        count = userService.countAllUsers();
        Assert.assertTrue(count == 1);
    }
}
