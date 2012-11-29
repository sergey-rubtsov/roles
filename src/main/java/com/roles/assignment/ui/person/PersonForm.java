package com.roles.assignment.ui.person;

import com.roles.assignment.domain.Person;
import com.roles.assignment.service.PersonService;
import com.roles.assignment.ui.EntityForm;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.Arrays;

@SuppressWarnings("serial")
@Configurable(preConstruction=true)
public class PersonForm extends VerticalLayout implements EntityForm<Person> {

    @Autowired
    PersonService personService;

    Person person;

    private final Form personForm;

    public PersonForm() {
        person = new Person();
        BeanItem<Person> personItem = new BeanItem<Person>(person);
        personForm = new Form();
        personForm.setWriteThrough(false);
        personForm.setInvalidCommitted(false);
        personForm.setFormFieldFactory(new PersonFieldFactory());
        personForm.setItemDataSource(personItem);
        personForm.setVisibleItemProperties(Arrays.asList(new String[]{
                "firstName", "lastName", "phoneNumber", "birthdate"}));
        addComponent(personForm);
    }

    @Override
    public void save() {
        personService.savePerson(getEntity());
    }

    @Override
    public Person getEntity() {
        personForm.commit();
        return person;
    }

    @Override
    public void discard() {
        personForm.discard();
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
                tf.addValidator(new StringLengthValidator(
                        "First Name must be 1-25 characters", 1, 25, false));
            } else if ("lastName".equals(propertyId)) {
                TextField tf = (TextField) f;
                tf.setRequired(true);
                tf.setRequiredError("Please enter a Last Name");
                tf.addValidator(new StringLengthValidator(
                        "Last Name must be 1-50 characters", 1, 50, false));
            } else if ("email".equals(propertyId)) {
                TextField tf = (TextField) f;
                tf.setRequired(true);
                tf.setRequiredError("Please enter a Email");
                tf.addValidator(new StringLengthValidator(
                        "Last Name must be 3-50 characters", 3, 50, false));
            }
            return f;
        }
    }
}