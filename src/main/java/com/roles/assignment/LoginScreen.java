package com.roles.assignment;

import com.roles.assignment.ui.AdminWindow;
import com.vaadin.ui.*;
import org.apache.shiro.authc.*;

@SuppressWarnings("serial")
public class LoginScreen extends VerticalLayout {

    public LoginScreen(final StartApplication app) {
        setSizeFull();

        Panel loginPanel = new Panel("Login");
        loginPanel.setWidth("400px");

        LoginForm loginForm = new LoginForm();
        loginForm.setPasswordCaption("Password");
        loginForm.setUsernameCaption("User");
        loginForm.setLoginButtonCaption("Log in");

        loginForm.setHeight("120px");
        loginForm.addListener(new ShiroLoginListener(app, loginForm));

        loginPanel.addComponent(loginForm);

        addComponent(loginPanel);
        setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);

        HorizontalLayout footer = new HorizontalLayout();
        footer.setHeight("50px");
        addComponent(footer);
    }

    private static final class ShiroLoginListener implements LoginForm.LoginListener {

        private final StartApplication app;
        private final LoginForm loginForm;

        public ShiroLoginListener(final StartApplication app, final LoginForm loginForm) {
            this.app = app;
            this.loginForm = loginForm;
        }

        @Override
        public void onLogin(final LoginForm.LoginEvent event) {
            final String username = event.getLoginParameter("username");
            final String password = event.getLoginParameter("password");

            app.getMainWindow().setContent(new AdminWindow(app));

            try {
/*                app.login(username, password);
                Subject currentUser = SecurityUtils.getSubject();
                if (currentUser.hasRole(SystemRoles.ROLE_ADMIN.roleValue())) {
                    app.getMainWindow().setContent(new AdminWidgetToDelete(app));
                }
                if (currentUser.hasRole(SystemRoles.ROLE_USER.roleValue())) {
                    app.getMainWindow().setContent(new PersonWidget(app));
                }*/
            } catch (UnknownAccountException uae) {
                this.loginForm.getWindow().showNotification("Invalid User " + uae, Window.Notification.TYPE_ERROR_MESSAGE);
            } catch (IncorrectCredentialsException ice) {
                this.loginForm.getWindow().showNotification("Invalid User " + ice, Window.Notification.TYPE_ERROR_MESSAGE);
            } catch (LockedAccountException lae) {
                this.loginForm.getWindow().showNotification("Invalid User " + lae, Window.Notification.TYPE_ERROR_MESSAGE);
            } catch (ExcessiveAttemptsException eae) {
                this.loginForm.getWindow().showNotification("Invalid User " + eae, Window.Notification.TYPE_ERROR_MESSAGE);
            } catch (AuthenticationException ae) {
                this.loginForm.getWindow().showNotification("Invalid User " + ae, Window.Notification.TYPE_ERROR_MESSAGE);
            } catch (Exception ex) {
                this.loginForm.getWindow().showNotification("Exception " + ex.getMessage(), Window.Notification.TYPE_ERROR_MESSAGE);
            }
        }
    }
}
