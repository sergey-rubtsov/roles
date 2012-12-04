package com.roles.assignment.ui.person;

import com.roles.assignment.domain.User;
import com.roles.assignment.service.UserService;
import com.roles.assignment.ui.EntityForm;
import com.roles.assignment.ui.entity.SummaryUserData;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.Arrays;

@SuppressWarnings("serial")
@Configurable(preConstruction=true)
public class UserForm extends VerticalLayout implements EntityForm<User> {

    @Autowired
    UserService userService;

    SummaryUserData userData;

    final Form userForm;

    public UserForm() {
        userData = new SummaryUserData();
        BeanItem<SummaryUserData> personItem = new BeanItem<SummaryUserData>(userData);
        userForm = new Form();
        userForm.setWriteThrough(false);
        userForm.setInvalidCommitted(false);
        userForm.setFormFieldFactory(new PersonFieldFactory());
        userForm.setItemDataSource(personItem);
        userForm.setVisibleItemProperties(Arrays.asList(new String[]{
                "login", "password", "email", "firstName", "lastName", "phoneNumber", "birthdate"}));
        addComponent(userForm);
    }

    @Override
    public void save() {
        userService.saveUser(getEntity(), userData.getPerson());
    }

    @Override
    public User getEntity() {
        userForm.commit();
        return userData.getUser();
    }

    @Override
    public void discard() {
        userForm.discard();
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