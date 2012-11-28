package com.roles.assignment.ui.person;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * User: RubtsovSL
 * Date: 28.11.12
 * Time: 13:27
 */
public class CommandForm extends VerticalLayout implements
        Button.ClickListener {

    private Button newPerson = new Button("Add person");
    private Button newUser = new Button("Add person");
    private Button find = new Button("Search");
    private Button share = new Button("Share");
    private PersonWidget personWidget;

    public CommandForm(PersonWidget personWidget) {
        addComponent(new Label("Commands"));
        this.personWidget = personWidget;
        newPerson.setIcon(new ThemeResource("icons/32/document-add.png"));
        newPerson.addListener(this);
        addComponent(newPerson);
        newUser.setIcon(new ThemeResource("icons/32/document-add.png"));
        newUser.addListener(this);
        addComponent(newUser);
        find.setIcon(new ThemeResource("icons/32/folder-add.png"));
        find.addListener(this);
        addComponent(find);
        share.setIcon(new ThemeResource("icons/32/users.png"));
        share.addListener(this);
        addComponent(share);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == find) {
            personWidget.setLeftForm(PersonWidget.FormType.FIND);
        } else if (source == newPerson) {
            personWidget.setLeftForm(PersonWidget.FormType.PERSON);
        } else if (source == newUser) {
            personWidget.setLeftForm(PersonWidget.FormType.USER);
        }
    }
}
