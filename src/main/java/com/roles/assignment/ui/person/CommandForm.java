package com.roles.assignment.ui.person;

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
    private Button newUser = new Button("Add user");
    private Button find = new Button("Search");
    private Button share = new Button("Share");
    private PersonWidget personWidget;

    public CommandForm(PersonWidget personWidget) {
        addComponent(new Label("Commands"));
        this.personWidget = personWidget;
        newPerson.addListener(this);
        newPerson.setWidth("120px");
        addComponent(newPerson);
        newUser.addListener(this);
        newUser.setWidth("120px");
        addComponent(newUser);
        find.addListener(this);
        find.setWidth("120px");
        addComponent(find);
        share.addListener(this);
        share.setWidth("120px");
        addComponent(share);
        this.setSpacing(true);
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
