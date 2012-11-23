package com.roles.assignment.ui;

import com.roles.assignment.StartApplication;
import com.roles.assignment.ui.persons.PersonWidget;
import com.roles.assignment.ui.roles.AddPersonWindow;
import com.vaadin.ui.*;

public class AdminWindow extends CustomComponent {

    private AbsoluteLayout mainLayout;
    private AbsoluteLayout body;
    private TabSheet tabs;
    private AbsoluteLayout logs;
    private AbsoluteLayout roleManage;
    private AbsoluteLayout usersLayout;
    private HorizontalLayout controlUsers;
    private VerticalLayout userEntities;
    private AbsoluteLayout treeLayout;
    private HorizontalLayout controlTreeButtons;
    private Tree tree;
    private HorizontalLayout title;
    private Label userLabel;
    private HorizontalLayout mainButtonsPanel;
    private Button saveButton;
    private Button undoButton;
    private Button addPersonButton;
    private Button addUserButton;
    private Button addObjectButton;
    private AbsoluteLayout usersManage;
    private StartApplication app;

    public AdminWindow(StartApplication app) {
    //public AdminWindow() {
        super();
        this.app = app;
        buildMainLayout();
        setCompositionRoot(mainLayout);
    }

    private AbsoluteLayout buildMainLayout() {
        // common part: create layout
        mainLayout = new AbsoluteLayout();
        mainLayout.setImmediate(false);
        mainLayout.setWidth("100%");
        mainLayout.setHeight("100%");
        mainLayout.setMargin(false);

        // top-level component properties
        setWidth("100.0%");
        setHeight("100.0%");

        // body
        body = buildBody();
        mainLayout.addComponent(body,
                "top:10.0px;right:15.0px;bottom:10.0px;left:15.0px;");

        return mainLayout;
    }


    private AbsoluteLayout buildBody() {
        // common part: create layout
        body = new AbsoluteLayout();
        body.setImmediate(false);
        body.setWidth("100.0%");
        body.setHeight("100.0%");
        body.setMargin(false);

        // title
        title = buildTitle();
        body.addComponent(title, "top:0.0px;right:20.0px;left:20.0px;");

        // tabs
        tabs = buildTabs();
        body.addComponent(tabs,
                "top:30.0px;right:20.0px;bottom:0.0px;left:20.0px;");

        return body;
    }


    private HorizontalLayout buildTitle() {
        // common part: create layout
        title = new HorizontalLayout();
        title.setImmediate(false);
        title.setWidth("100.0%");
        title.setHeight("30px");
        title.setMargin(false);

        // mainButtonsPanel
        mainButtonsPanel = buildMainButtonsPanel();
        title.addComponent(mainButtonsPanel);

        // userLabel
        userLabel = new Label();
        userLabel.setImmediate(false);
        userLabel.setWidth("-1px");
        userLabel.setHeight("-1px");
        userLabel.setValue("User");
        title.addComponent(userLabel);
        title.setComponentAlignment(userLabel, new Alignment(6));

        return title;
    }


    private HorizontalLayout buildMainButtonsPanel() {
        // common part: create layout
        mainButtonsPanel = new HorizontalLayout();
        mainButtonsPanel.setImmediate(false);
        mainButtonsPanel.setWidth("-1px");
        mainButtonsPanel.setHeight("-1px");
        mainButtonsPanel.setMargin(false);
        mainButtonsPanel.setSpacing(true);

        // undoButton
        undoButton = new Button();
        undoButton.setCaption("Undo");
        undoButton.setImmediate(false);
        undoButton.setWidth("-1px");
        undoButton.setHeight("-1px");
        mainButtonsPanel.addComponent(undoButton);

        // saveButton
        saveButton = new Button();
        saveButton.setCaption("Save");
        saveButton.setImmediate(false);
        saveButton.setWidth("-1px");
        saveButton.setHeight("-1px");
        mainButtonsPanel.addComponent(saveButton);

        return mainButtonsPanel;
    }


    private TabSheet buildTabs() {
        // common part: create layout
        tabs = new TabSheet();
        tabs.setImmediate(true);
        tabs.setWidth("100.0%");
        tabs.setHeight("100.0%");

        // usersManage
        usersManage = buildUsersManage();
        tabs.addTab(usersManage, "Users", null);

        // roleManage
        roleManage = buildRoleManage();
        tabs.addTab(roleManage, "Roles", null);

        // logs
        logs = buildLogs();
        tabs.addTab(logs, "Logs", null);

        return tabs;
    }

    private AbsoluteLayout buildUsersManage() {
        AbsoluteLayout usersManage = new AbsoluteLayout();
        usersManage.setStyleName("main");
        usersManage.setImmediate(false);
        usersManage.setWidth("100.0%");
        usersManage.setHeight("100.0%");
        usersManage.setMargin(false);
        usersManage.addComponent(new PersonWidget(null));
        return usersManage;
    }

    private AbsoluteLayout buildLogs() {
        AbsoluteLayout logs = new AbsoluteLayout();
        logs.setStyleName("main");
        logs.setImmediate(false);
        logs.setWidth("100.0%");
        logs.setHeight("100.0%");
        logs.setMargin(false);
        return logs;
    }

    private AbsoluteLayout buildRoleManage() {
        // common part: create layout
        AbsoluteLayout roleManage = new AbsoluteLayout();
        roleManage.setStyleName("main");
        roleManage.setImmediate(false);
        roleManage.setWidth("100.0%");
        roleManage.setHeight("100.0%");
        roleManage.setMargin(false);

        // treeLayout
        treeLayout = buildTreeLayout();
        roleManage.addComponent(treeLayout,
                "top:0.0px;bottom:0.0px;left:10.0px;");

        // usersLayout
        usersLayout = buildUsersLayout();
        roleManage.addComponent(usersLayout,
                "top:0.0px;right:10.0px;bottom:0.0px;");

        return roleManage;
    }


    private AbsoluteLayout buildTreeLayout() {
        // common part: create layout
        treeLayout = new AbsoluteLayout();
        treeLayout.setImmediate(false);
        treeLayout.setWidth("70.0%");
        treeLayout.setHeight("100.0%");
        treeLayout.setMargin(false);

        // tree
        tree = new Tree();
        tree.setImmediate(false);
        tree.setWidth("100.0%");
        tree.setHeight("100.0%");
        treeLayout.addComponent(tree, "top:10.0px;bottom:35.0px;left:0.0px;");

        // controlTreeButtons
        controlTreeButtons = new HorizontalLayout();
        controlTreeButtons.setImmediate(false);
        controlTreeButtons.setWidth("100.0%");
        controlTreeButtons.setHeight("35px");
        controlTreeButtons.setMargin(false);
        treeLayout.addComponent(controlTreeButtons, "bottom:0.0px;left:0.0px;");

        // controlTreeButtons
        controlTreeButtons = buildControlTreeButtons();
        treeLayout.addComponent(controlTreeButtons, "bottom:0.0px;left:0.0px;");

        return treeLayout;
    }

    private HorizontalLayout buildControlTreeButtons() {
        // common part: create layout
        controlTreeButtons = new HorizontalLayout();
        controlTreeButtons.setImmediate(false);
        controlTreeButtons.setWidth("100.0%");
        controlTreeButtons.setHeight("35px");
        controlTreeButtons.setMargin(false);

        // addObjectButton
        addObjectButton = new Button();
        addObjectButton.setCaption("+");
        addObjectButton.setImmediate(false);
        addObjectButton.setWidth("-1px");
        addObjectButton.setHeight("-1px");
        controlTreeButtons.addComponent(addObjectButton);
        controlTreeButtons.setComponentAlignment(addObjectButton,
                new Alignment(33));

        return controlTreeButtons;
    }


    private AbsoluteLayout buildUsersLayout() {
        // common part: create layout
        usersLayout = new AbsoluteLayout();
        usersLayout.setImmediate(false);
        usersLayout.setWidth("30.0%");
        usersLayout.setHeight("100.0%");
        usersLayout.setMargin(false);

        // userEntities
        userEntities = new VerticalLayout();
        userEntities.setImmediate(false);
        userEntities.setWidth("100.0%");
        userEntities.setHeight("100.0%");
        userEntities.setMargin(false);
        usersLayout.addComponent(userEntities,
                "top:10.0px;bottom:35.0px;left:0.0px;");

        // controlUsers
        controlUsers = new HorizontalLayout();
        controlUsers.setImmediate(false);
        controlUsers.setWidth("100.0%");
        controlUsers.setHeight("35px");
        controlUsers.setMargin(false);
        usersLayout.addComponent(controlUsers, "bottom:0.0px;left:0.0px;");

        // controlUsers
        controlUsers = buildControlUsers();
        usersLayout.addComponent(controlUsers, "bottom:0.0px;left:0.0px;");

        return usersLayout;
    }

    private HorizontalLayout buildControlUsers() {
        // common part: create layout
        controlUsers = new HorizontalLayout();
        controlUsers.setImmediate(false);
        controlUsers.setWidth("100.0%");
        controlUsers.setHeight("35px");
        controlUsers.setMargin(false);

        // addPersonButton
        addPersonButton = new Button("Add person", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                AddPersonWindow newPerson = new AddPersonWindow(false);
                newPerson.center();
                getWindow().addWindow(newPerson);
            }
        });
        addPersonButton.setImmediate(false);
        addPersonButton.setWidth("-1px");
        addPersonButton.setHeight("-1px");
        controlUsers.addComponent(addPersonButton);
        controlUsers.setComponentAlignment(addPersonButton, new Alignment(33));

        // addUserButton
        addUserButton = new Button("Add user", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                AddPersonWindow newPerson = new AddPersonWindow(true);
                newPerson.center();
                getWindow().addWindow(newPerson);
            }
        });
        addUserButton.setImmediate(false);
        addUserButton.setWidth("-1px");
        addUserButton.setHeight("-1px");
        controlUsers.addComponent(addUserButton);
        controlUsers.setComponentAlignment(addUserButton, new Alignment(33));

        return controlUsers;
    }
}


