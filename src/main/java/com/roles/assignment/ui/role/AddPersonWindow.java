package com.roles.assignment.ui.role;

import com.roles.assignment.ui.person.PersonForm;
import com.roles.assignment.ui.person.UserForm;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
/**
 * User: RubtsovSL
 * Date: 01.11.12
 * Time: 11:20
 */
public class AddPersonWindow extends Window {

    public AddPersonWindow(boolean userCredentials) {
        setClosable(true);
        addListener(new Window.CloseListener() {
            public void windowClose(CloseEvent e) {
            }
        });
        VerticalLayout layout = (VerticalLayout) getContent();
        //layout.setMargin(true);
        layout.setSizeUndefined();
        VerticalLayout addPersonLayout = new VerticalLayout();
        addPersonLayout.addComponent(new PersonForm());
        if (userCredentials) {
            addPersonLayout.addComponent(new UserForm());
        }
        addComponent(addPersonLayout);
    }
}




