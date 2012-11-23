package com.roles.assignment.ui.roles;

import com.roles.assignment.domain.Person;
import com.roles.assignment.service.PersonService;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.BaseTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

@SuppressWarnings("serial")
public class AddUserForm extends VerticalLayout {

    @Autowired
    PersonService personService;

    Person person;

    private static final String COMMON_FIELD_WIDTH = "12em";

    public AddUserForm() {
        //Эту форму отдельно и с полем, где можно выбрать Person
        person = new Person();
        BeanItem<Person> personItem = new BeanItem<Person>(person);

        final Form personForm = new Form();
        personForm.setCaption("User details");
        personForm.setWriteThrough(false);
        personForm.setInvalidCommitted(false);


        personForm.setFormFieldFactory(new PersonFieldFactory());
        personForm.setItemDataSource(personItem);

        personForm.setVisibleItemProperties(Arrays.asList(new String[]{
                "login", "password", "email", "role"}));

        addComponent(personForm);

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        Button discardChanges = new Button("Discard changes",
                new Button.ClickListener() {
                    public void buttonClick(ClickEvent event) {
                        personForm.discard();
                    }
                });
        discardChanges.setStyleName(BaseTheme.BUTTON_LINK);
        buttons.addComponent(discardChanges);
        buttons.setComponentAlignment(discardChanges, Alignment.MIDDLE_LEFT);

        Button apply = new Button("Apply", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                    personForm.commit();
                    personService.savePerson(person);
                    //showPojoState();
                } catch (Exception e) {
                    // Ignored, we'll let the Form handle the errors
                }
            }
        });
        buttons.addComponent(apply);

        CheckBox addUserCredentials = new CheckBox("Add User Credentials", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                //create me
            }
        });

        personForm.getFooter().addComponent(buttons);
        personForm.getFooter().addComponent(addUserCredentials);
        personForm.getFooter().setMargin(false, false, true, true);
    }

    private class PersonFieldFactory extends DefaultFieldFactory {

        @Override
        public Field createField(Item item, Object propertyId,
                                 Component uiContext) {
            Field f;
            if ("login".equals(propertyId)) {
                f = super.createField(item, propertyId, uiContext);
                TextField tf = (TextField) f;
                tf.setRequired(true);
                tf.setRequiredError("Please enter a First Name");
                tf.setWidth(COMMON_FIELD_WIDTH);
                tf.addValidator(new StringLengthValidator(
                        "Login must be 1-25 characters", 1, 25, false));
            } else if ("password".equals(propertyId)) {
                f = createPasswordField(propertyId);
            } else {
                f = super.createField(item, propertyId, uiContext);
            }
            return f;
        }

        private PasswordField createPasswordField(Object propertyId) {
            PasswordField pf = new PasswordField();
            pf.setCaption(DefaultFieldFactory
                    .createCaptionByPropertyId(propertyId));
            return pf;
        }
    }
}