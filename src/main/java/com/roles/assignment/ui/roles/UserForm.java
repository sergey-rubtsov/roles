package com.roles.assignment.ui.roles;

import com.roles.assignment.domain.User;
import com.roles.assignment.service.UserService;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.BaseTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

@SuppressWarnings("serial")
public class UserForm extends VerticalLayout {

    @Autowired
    UserService userService;

    User user;

    private static final String COMMON_FIELD_WIDTH = "12em";

    public UserForm() {
        user = new User();
        BeanItem<User> personItem = new BeanItem<User>(user);

        final Form userForm = new Form();
        userForm.setCaption("User details");
        userForm.setWriteThrough(false);
        userForm.setInvalidCommitted(false);


        userForm.setFormFieldFactory(new PersonFieldFactory());
        userForm.setItemDataSource(personItem);

        userForm.setVisibleItemProperties(Arrays.asList(new String[]{
                "login", "password", "email", "role"}));

        addComponent(userForm);

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        Button discardChanges = new Button("Discard changes",
                new Button.ClickListener() {
                    public void buttonClick(ClickEvent event) {
                        userForm.discard();
                    }
                });
        discardChanges.setStyleName(BaseTheme.BUTTON_LINK);
        buttons.addComponent(discardChanges);
        buttons.setComponentAlignment(discardChanges, Alignment.MIDDLE_LEFT);

        Button apply = new Button("Apply", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                    userForm.commit();
                    userService.saveUser(user);
                } catch (Exception e) {
                }
            }
        });
        buttons.addComponent(apply);
        userForm.getFooter().addComponent(buttons);
        userForm.getFooter().setMargin(false, false, true, true);
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