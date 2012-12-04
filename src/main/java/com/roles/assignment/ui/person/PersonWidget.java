package com.roles.assignment.ui.person;

import com.roles.assignment.StartApplication;
import com.roles.assignment.Widget;
import com.roles.assignment.domain.Person;
import com.roles.assignment.service.PersonService;
import com.roles.assignment.service.UserService;
import com.roles.assignment.ui.EntityForm;
import com.roles.assignment.ui.help.HelpWindow;
import com.roles.assignment.ui.persons.PersonReferenceContainer;
import com.roles.assignment.ui.persons.SearchFilter;
import com.roles.assignment.ui.persons.SearchView;
import com.roles.assignment.ui.persons.SharingOptions;
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
    private Button search = new Button("Search");
    private Button share = new Button("Share");
    private Button help = new Button("Help");
    private Button main = new Button("Main");
    private Button save = new Button("Save");
    private Button discard = new Button("Discard");
    private HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();

    private LeftForm leftForm;
    private PersonList personList = null;
    private CommandForm commandForm = null;
    private PersonForm personForm = null;
    private UserForm userForm = null;
    private HelpWindow helpWindow = null;
    private StartApplication app;

    @Autowired
    PersonService personService;

    @Autowired
    UserService userService;

    private PersonReferenceContainer personDataSource;

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
        main.addListener((Button.ClickListener) this);
        save.addListener((Button.ClickListener) this);
        discard.addListener((Button.ClickListener) this);

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
        if (type == FormType.COMMANDS) {
            getLeftForm().removeAllComponents();
            leftForm.addComponent(getCommandForm());
            return leftForm;
        } else
        if (type == FormType.PERSON) {
            getLeftForm().setForm(getPersonForm());
            leftForm.addComponent(main);
            return leftForm;
        } else
        if (type == FormType.USER) {
            getLeftForm().setForm(getUserForm());
            leftForm.addComponent(main);
            return leftForm;
        } else
        if (type == FormType.FIND) {
            getLeftForm().removeAllComponents();
            leftForm.addComponent(new Label(type.name()));
            leftForm.addComponent(main);
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
    private CommandForm getCommandForm() {
        if (commandForm == null) {
            commandForm = new CommandForm(this);
        } return commandForm;
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

    SharingOptions sharingOptions = null;

    private SharingOptions getSharingOptions() {
        if (sharingOptions == null) {
            sharingOptions = new SharingOptions();
        }
        return sharingOptions;
    }

    private SearchView searchView = null;

    private SearchView getSearchView() {
        if (searchView == null) {
            searchView = new SearchView(this);
        }
        return searchView;
    }

    private HorizontalLayout createToolbar() {
        HorizontalLayout lo = new HorizontalLayout();

        newPerson.setIcon(new ThemeResource("icons/32/document-add.png"));
        newPerson.addListener((Button.ClickListener) this);
        lo.addComponent(newPerson);
        search.setIcon(new ThemeResource("icons/32/folder-add.png"));
        search.addListener((Button.ClickListener) this);
        lo.addComponent(search);
        share.setIcon(new ThemeResource("icons/32/users.png"));
        share.addListener((Button.ClickListener) this);
        lo.addComponent(share);
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

    private void showSearchView() {
        horizontalSplit.setSecondComponent(getSearchView());
    }

    @Override
    public PersonReferenceContainer getDataSource() {
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
            showSearchView();
        } else if (source == newPerson) {
            addNewContanct();
        } else if (source == help) {
            app.getMainWindow().addWindow(getHelpWindow());
        } else if (source == share) {
            app.getMainWindow().addWindow(getSharingOptions());
        } else if (source == main) {
            setLeftForm(FormType.COMMANDS);
        } else if (source == discard) {
            leftForm.getForm().discard();
        } else if (source == save) {
            leftForm.getForm().save();
        }
    }

    private void showListView() {
        //setMainComponent(getListView());
    }

    private void addNewContanct() {
        showListView();
        //personForm.addContact();
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