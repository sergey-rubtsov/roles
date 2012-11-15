package com.roles.assignment;

import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import com.roles.assignment.domain.Person;
import com.roles.assignment.domain.SystemRoles;
import com.roles.assignment.domain.User;
import com.roles.assignment.service.PersonService;
import com.roles.assignment.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.dellroad.stuff.vaadin.SpringContextApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.ConfigurableWebApplicationContext;

@Configurable(preConstruction=true)
public class StartApplication extends SpringContextApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartApplication.class);

    @Autowired
    UserService userService;

    @Autowired
    PersonService personService;

    @Override
    protected void initSpringApplication(ConfigurableWebApplicationContext context) {
        if (userService.countAllUsers() == 0) {
            initDataBase();
        }
        createMainLayout();
    }

    private void initDataBase() {
        User user = new User();
        user.setLogin(SystemRoles.ROLE_USER.roleValue());
        user.setPassword(SystemRoles.ROLE_USER.roleValue());
        //userService.saveUser(user);
        userService.addSystemUserRole(user);
        User admin = new User();
        admin.setLogin(SystemRoles.ROLE_ADMIN.roleValue());
        admin.setPassword(SystemRoles.ROLE_ADMIN.roleValue());
        //userService.saveUser(admin);
        userService.addSystemAdminRole(admin);

        Person test1 = new Person();
    }

    private void createMainLayout() {
        setTheme("contacts");
        final Window window = new Window();
        setMainWindow(window);
        window.setContent(new LoginScreen(this));
    }

    public void login(String username, String password) {
        UsernamePasswordToken token;

        token = new UsernamePasswordToken(username, password);
        // ”Remember Me” built-in, just do this:
        token.setRememberMe(true);

        // With most of Shiro, you'll always want to make sure you're working with the currently executing user,
        // referred to as the subject
        Subject currentUser = SecurityUtils.getSubject();

        // Authenticate
        currentUser.login(token);
    }

    public void logout() {
        getMainWindow().getApplication().close();
        final Subject currentUser = SecurityUtils.getSubject();
        LOGGER.debug("User "+ currentUser.getPrincipal() + " logging out");

        if (currentUser.isAuthenticated()) {
            currentUser.logout();
        }
    }

    public static class LogoutListener implements Button.ClickListener {
        private final StartApplication app;

        public LogoutListener(final StartApplication app) {
            this.app = app;
        }


        @Override
        public void buttonClick(final Button.ClickEvent event) {
            this.app.logout();
        }
    }
}