package com.roles.assignment.ui.windows;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

public class AdminWindow extends CustomComponent {

    private AbsoluteLayout mainLayout;

    private AbsoluteLayout body;

    private TabSheet tabs;

    private AbsoluteLayout logs;

    private AbsoluteLayout datamanage;

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

    private Button addUserButton;

    private Button addObjectButton;

    public AdminWindow() {
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

        // datamanage
        datamanage = buildDatamanage();
        tabs.addTab(datamanage, "Entities", null);

        // logs
        logs = new AbsoluteLayout();
        logs.setStyleName("main");
        logs.setImmediate(false);
        logs.setWidth("100.0%");
        logs.setHeight("100.0%");
        logs.setMargin(false);
        tabs.addTab(logs, "Logs", null);

        return tabs;
    }


    private AbsoluteLayout buildDatamanage() {
        // common part: create layout
        datamanage = new AbsoluteLayout();
        datamanage.setStyleName("main");
        datamanage.setImmediate(false);
        datamanage.setWidth("100.0%");
        datamanage.setHeight("100.0%");
        datamanage.setMargin(false);

        // treeLayout
        treeLayout = buildTreeLayout();
        datamanage.addComponent(treeLayout,
                "top:0.0px;bottom:0.0px;left:10.0px;");

        // usersLayout
        usersLayout = buildUsersLayout();
        datamanage.addComponent(usersLayout,
                "top:0.0px;right:10.0px;bottom:0.0px;");

        return datamanage;
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

        // addUserButton
        addUserButton = new Button("Add person", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                AddPersonWindow newPerson = new AddPersonWindow();
                newPerson.center();
                getWindow().addWindow(newPerson);
            }
        });
        //addUserButton.setCaption("+");
        addUserButton.setImmediate(false);
        addUserButton.setWidth("-1px");
        addUserButton.setHeight("-1px");
        controlUsers.addComponent(addUserButton);
        controlUsers.setComponentAlignment(addUserButton, new Alignment(33));

        return controlUsers;
    }
}


