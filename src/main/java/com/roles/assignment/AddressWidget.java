package com.roles.assignment;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window.Notification;
import com.roles.assignment.domain.Person;
import com.roles.assignment.service.PersonService;
import com.roles.assignment.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable(preConstruction=true)
public class AddressWidget extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener, ItemClickListener, Widget {
    private Button newContact = new Button("Add contact");
    private Button search = new Button("Search");
    private Button share = new Button("Share");
    private Button help = new Button("Help");
    private HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();
    private NavigationTree tree = new NavigationTree(this);
    private ListView listView = null;
    private PersonList personList = null;
    private PersonForm personForm = null;
    private HelpWindow helpWindow = null;
    private StartApplication app;

    @Autowired
    PersonService personService;

    private PersonReferenceContainer personDataSource;

    private static final long serialVersionUID = -1481094776783567319L;

    public AddressWidget(StartApplication app) {
        super();
        this.app = app;
        createMainLayout();
        final Button logout = new Button("logout");
        logout.addListener(new StartApplication.LogoutListener(app));
    }

    private void createMainLayout() {
        this.setSizeFull();
        this.addComponent(createToolbar());
        this.addComponent(horizontalSplit);
        /* Allocate all available extra space to the horizontal split panel */
        this.setExpandRatio(horizontalSplit, 1);
        /*
           * Set the initial split position so we can have a 200 pixel menu to the
           * left
           */
        horizontalSplit.setSplitPosition(150, HorizontalSplitPanel.UNITS_PIXELS);
        horizontalSplit.setFirstComponent(tree);
        personDataSource = new PersonReferenceContainer(personService);
        getDataSource().refresh();
        setMainComponent(getListView());
        app.getMainWindow().setContent(this);

    }

    // lazy initialization pattern
    private ListView getListView() {
        if (listView == null) {
            personList = new PersonList(this);
            personForm = new PersonForm(this);
            listView = new ListView(personList, personForm);
        }
        return listView;
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

        newContact.setIcon(new ThemeResource("icons/32/document-add.png"));
        newContact.addListener((Button.ClickListener) this);
        lo.addComponent(newContact);
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
        setMainComponent(getSearchView());
    }

    private void setMainComponent(Component c) {
        horizontalSplit.setSecondComponent(c);
    }

    @Override
    public PersonReferenceContainer getDataSource() {
        return personDataSource;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();
        if (property == personList) {
            Person person = personService.findPerson((String) personList.getValue());
            personForm.editContact(person);
        }
    }

    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == search) {
            showSearchView();
        } else if (source == newContact) {
            addNewContanct();
        } else if (source == help) {
            app.getMainWindow().addWindow(getHelpWindow());
        } else if (source == share) {
            app.getMainWindow().addWindow(getSharingOptions());
        }
    }

    public void itemClick(ItemClickEvent event) {
        if (event.getSource() == tree) {
            Object itemId = event.getItemId();
            if (itemId != null) {
                if (NavigationTree.SHOW_ALL.equals(itemId)) {
                    getDataSource().refresh(PersonReferenceContainer.defaultQueryMetaData);
                    showListView();
                } else if (NavigationTree.SEARCH.equals(itemId)) {
                    showSearchView();
                } else if (itemId instanceof SearchFilter) {
                    search((SearchFilter) itemId);
                }
            }
        }
    }

    private void showListView() {
        setMainComponent(getListView());
    }

    private void addNewContanct() {
        showListView();
        personForm.addContact();
    }

    @Override
    public void saveSearch(SearchFilter searchFilter) {
        tree.addItem(searchFilter);
        tree.setParent(searchFilter, NavigationTree.SEARCH);
        // mark the saved search as a leaf (cannot have children)
        tree.setChildrenAllowed(searchFilter, false);
        // make sure "Search" is expanded
        tree.expandItem(NavigationTree.SEARCH);
        // select the saved search
        tree.setValue(searchFilter);
    }

    @Override
    public void search(SearchFilter searchFilter) {
        QueryMetaData qmd = new QueryMetaData(
                (String) searchFilter.getPropertyId(), searchFilter.getTerm(),
                getDataSource().getQueryMetaData().getOrderBy(),
                getDataSource().getQueryMetaData().getAscending());
        getDataSource().refresh(qmd);
        showListView();
        showNotification(searchFilter);
    }

    private void showNotification(SearchFilter searchFilter) {
        if (getDataSource().size() > 0) {
            app.getMainWindow().showNotification(
                    "Searched for " + searchFilter.getPropertyId() + "=*"
                            + searchFilter.getTerm() + "*, found "
                            + getDataSource().size() + " item(s).",
                    Notification.TYPE_TRAY_NOTIFICATION);
        } else {
            app.getMainWindow().showNotification(
                    "Searched for " + searchFilter.getPropertyId() + "=*"
                            + searchFilter.getTerm() + "*, person not found.",
                    Notification.TYPE_TRAY_NOTIFICATION);
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
}