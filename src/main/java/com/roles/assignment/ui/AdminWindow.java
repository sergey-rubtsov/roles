package com.roles.assignment.ui;

import com.roles.assignment.StartApplication;
import com.roles.assignment.ui.persons.PersonWidget;
import com.vaadin.ui.*;

public class AdminWindow extends CustomComponent {

    private AbsoluteLayout mainLayout;
    private AbsoluteLayout body;
    private TabSheet tabs;
    private AbsoluteLayout logs;
    private AbsoluteLayout roleManage;
    private HorizontalLayout title;
    private Label userLabel;
    private HorizontalLayout mainButtonsPanel;
    private Button saveButton;
    private Button undoButton;
    private AbsoluteLayout usersManage;
    private StartApplication app;

    public AdminWindow(StartApplication app) {
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

    private AbsoluteLayout buildRoleManage() {
        // common part: create layout
        AbsoluteLayout roleManage = new AbsoluteLayout();
        roleManage.setStyleName("main");
        roleManage.setImmediate(false);
        roleManage.setWidth("100.0%");
        roleManage.setHeight("100.0%");
        roleManage.setMargin(false);

        return roleManage;
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
}


