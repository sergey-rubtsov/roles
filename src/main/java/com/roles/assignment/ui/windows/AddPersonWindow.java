package com.roles.assignment.ui.windows;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
/**
 * User: RubtsovSL
 * Date: 01.11.12
 * Time: 11:20
 */
public class AddPersonWindow extends Window {

    public AddPersonWindow() {
        setClosable(true);
        addListener(new Window.CloseListener() {
            public void windowClose(CloseEvent e) {
            }
        });
        VerticalLayout layout = (VerticalLayout) getContent();
        //layout.setMargin(true);
        layout.setSizeUndefined();
        addComponent(new AddPersonForm());
    }
}




