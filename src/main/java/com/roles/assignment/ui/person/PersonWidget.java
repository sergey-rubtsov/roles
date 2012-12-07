package com.roles.assignment.ui.person;

import com.roles.assignment.StartApplication;
import com.roles.assignment.Widget;
import com.roles.assignment.domain.Person;
import com.roles.assignment.service.PersonService;
import com.roles.assignment.service.UserService;
import com.roles.assignment.ui.EntityForm;
import com.roles.assignment.ui.help.HelpWindow;
import com.roles.assignment.ui.persons.SearchFilter;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@SuppressWarnings("serial")
@Configurable(preConstruction=true)
public class PersonWidget extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener, ItemClickListener, Widget {
    private Button newPerson = new Button("Add person");
    private Button newUser = new Button("Add user");
    private Button search = new Button("Search");
    private Button help = new Button("Help");
    private Button save = new Button("Save");
    private Button discard = new Button("Discard");
    private HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();

    private LeftForm leftForm;
    private PersonList personList = null;
    private PersonForm personForm = null;
    private UserForm userForm = null;
    private HelpWindow helpWindow = null;
    private StartApplication app;

    @Autowired
    PersonService personService;

    @Autowired
    UserService userService;

    private PersonContainer personDataSource;

    private static final long serialVersionUID = -1481094776783567319L;

    public PersonWidget(StartApplication app) {
        super();
        this.app = app;
        createMainLayout();
    }

    private void createMainLayout() {
        this.setSizeFull();
        this.addComponent(createToolbar());
        this.addComponent(horizontalSplit);
        this.setExpandRatio(horizontalSplit, 1);
        horizontalSplit.setSplitPosition(270, HorizontalSplitPanel.UNITS_PIXELS);
        horizontalSplit.setFirstComponent(setLeftForm(FormType.COMMANDS));
        save.addListener((Button.ClickListener) this);
        discard.addListener((Button.ClickListener) this);
        personDataSource = new PersonContainer(personService);
        personDataSource.refresh();
        //personDataSource = new PersonReferenceContainer(personService);
        //getDataSource().refresh();
        //horizontalSplit.setSecondComponent(getPersonList());
    }

    public enum FormType {
        COMMANDS,
        PERSON,
        USER,
        FIND
    }
    
    private class LeftForm extends VerticalLayout {

        private EntityForm form;
        
        private Button save;
        
        private Button discard;
        
        private HorizontalLayout buttons;

        private LeftForm(Button save, Button discard) {
            this.save = save;
            this.discard = discard;
            buttons = new HorizontalLayout();
            buildButtonPanel();
        }

        public EntityForm getForm() {
            return form;
        }

        public void setForm(EntityForm form) {
            removeAllComponents();
            this.form = form;
            addComponent(form);
            buildButtonPanel();
        }

        private void buildButtonPanel() {
            buttons.addComponent(save);
            buttons.addComponent(discard);
            addComponent(buttons);
        }
    }

    public LeftForm setLeftForm(FormType type) {
        if (type == FormType.PERSON) {
            getLeftForm().setForm(getPersonForm());
            return leftForm;
        } else
        if (type == FormType.USER) {
            getLeftForm().setForm(getUserForm());
            return leftForm;
        } else
        if (type == FormType.FIND) {
            getLeftForm().removeAllComponents();
            leftForm.addComponent(new Label(type.name()));
            return leftForm;
        }
        return getLeftForm();
    }

    // lazy initialization pattern
    private LeftForm getLeftForm() {
        if (leftForm == null) {
            leftForm = new LeftForm(save, discard);
            leftForm.setMargin(true);
        }  return leftForm;
    }

    // lazy initialization pattern
    private PersonList getPersonList() {
        if (personList == null) {
            personList = new PersonList(this);
        } return personList;
    }

    // lazy initialization pattern
    private PersonForm getPersonForm() {
        if (personForm == null) {
            personForm = new PersonForm();
        } return personForm;
    }

    // lazy initialization pattern
    private UserForm getUserForm() {
        if (userForm == null) {
            userForm = new UserForm();
        } return userForm;
    }

    // lazy initialization pattern
    private HelpWindow getHelpWindow() {
        if (helpWindow == null) {
            helpWindow = new HelpWindow();
        }
        return helpWindow;
    }

    private HorizontalLayout createToolbar() {
        HorizontalLayout lo = new HorizontalLayout();

        newPerson.setIcon(new ThemeResource("icons/32/users.png"));
        newPerson.addListener((Button.ClickListener) this);
        lo.addComponent(newPerson);
        newUser.setIcon(new ThemeResource("icons/32/user.png"));
        newUser.addListener((Button.ClickListener) this);
        lo.addComponent(newUser);
        search.setIcon(new ThemeResource("icons/32/search.png"));
        search.addListener((Button.ClickListener) this);
        lo.addComponent(search);
        help.setIcon(new ThemeResource("icons/32/help.png"));
        help.addListener((Button.ClickListener) this);
        lo.addComponent(help);

        lo.setMargin(true);
        lo.setSpacing(true);
        lo.setWidth("100%");

        Embedded em = new Embedded("", new ThemeResource("images/logo.png"));
        lo.addComponent(em);
        lo.setComponentAlignment(em, Alignment.MIDDLE_RIGHT);
        lo.setExpandRatio(em, 1);
        lo.setStyleName("toolbar");
        return lo;
    }

    @Override
    public PersonContainer getDataSource() {
        return personDataSource;
    }

    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();
        if (property == personList) {
            Person person = personService.findPerson((String) personList.getValue());
            //personForm.editContact(person);
        }
    }

    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == search) {
            setLeftForm(PersonWidget.FormType.FIND);
        } else if (source == newPerson) {
            setLeftForm(PersonWidget.FormType.PERSON);
        } else if (source == help) {
            app.getMainWindow().addWindow(getHelpWindow());
        } else if (source == newUser) {
            setLeftForm(PersonWidget.FormType.USER);
        } else if (source == discard) {
            leftForm.getForm().discard();
        } else if (source == save) {
            leftForm.getForm().save();
        }
    }

    @Override
    public Object[] getNaturalColOrder() {
        return new Object[] {
                "firstName", "lastName", "email", "phoneNumber", "streetAddress",
                "postalCode", "city" };
    }

    @Override
    public String[] getColHeadersEnglish() {
        return new String[] {
                "First name", "Last name", "Email", "Phone number",
                "Street Address", "Postal Code", "City" };
    }

    @Override
    public void search(SearchFilter searchFilter) {
        //create me
    }


    @Override
    public void itemClick(ItemClickEvent event) {
        //create me
    }

    @Override
    public void saveSearch(SearchFilter searchFilter) {
        //create me
    }
}