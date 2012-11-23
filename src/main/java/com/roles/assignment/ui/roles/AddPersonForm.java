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
public class AddPersonForm extends VerticalLayout {

    @Autowired
    PersonService personService;

    Person person;

    private static final String COMMON_FIELD_WIDTH = "12em";

    public AddPersonForm(boolean userCredentials) {
        person = new Person();
        BeanItem<Person> personItem = new BeanItem<Person>(person);

        final Form personForm = new Form();
        personForm.setCaption("Personal details");
        personForm.setWriteThrough(false);
        personForm.setInvalidCommitted(false);


        personForm.setFormFieldFactory(new PersonFieldFactory());
        personForm.setItemDataSource(personItem);

        personForm.setVisibleItemProperties(Arrays.asList(new String[]{
                "firstName", "lastName", "phoneNumber", "birthdate"}));

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
        personForm.getFooter().addComponent(buttons);
        personForm.getFooter().setMargin(false, false, true, true);
    }

    private class PersonFieldFactory extends DefaultFieldFactory {

        @Override
        public Field createField(Item item, Object propertyId,
                                 Component uiContext) {
            Field f = super.createField(item, propertyId, uiContext);

            if ("firstName".equals(propertyId)) {
                TextField tf = (TextField) f;
                tf.setRequired(true);
                tf.setRequiredError("Please enter a First Name");
                tf.setWidth(COMMON_FIELD_WIDTH);
                tf.addValidator(new StringLengthValidator(
                        "First Name must be 1-25 characters", 1, 25, false));
            } else if ("lastName".equals(propertyId)) {
                TextField tf = (TextField) f;
                tf.setRequired(true);
                tf.setRequiredError("Please enter a Last Name");
                tf.setWidth(COMMON_FIELD_WIDTH);
                tf.addValidator(new StringLengthValidator(
                        "Last Name must be 1-50 characters", 1, 50, false));
            }
            return f;
        }

/*        private PasswordField createPasswordField(Object propertyId) {
            PasswordField pf = new PasswordField();
            pf.setCaption(DefaultFieldFactory
                    .createCaptionByPropertyId(propertyId));
            return pf;
        }*/
    }
}