package com.roles.assignment.ui.roles;

import com.roles.assignment.StartApplication;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;

/**
 * User: RubtsovSL
 * Date: 23.11.12
 * Time: 16:14
 */
public class RoleWidget extends VerticalLayout {

    private HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();
    private StartApplication app;
    private VerticalLayout usersLayout;
    private Tree tree;

    public RoleWidget(StartApplication app) {
        super();
        this.app = app;
        createMainLayout();
    }

    private void createMainLayout() {
        this.setSizeFull();
        this.addComponent(createToolbar());
        this.addComponent(horizontalSplit);
        this.setExpandRatio(horizontalSplit, 1);
        horizontalSplit.setSplitPosition(150, HorizontalSplitPanel.UNITS_PIXELS);

        // tree
        tree = buildTree();
        horizontalSplit.setFirstComponent(tree);

        // usersLayout
        usersLayout = buildUsersLayout();
        horizontalSplit.setSecondComponent(usersLayout);
    }

    private Tree buildTree() {
        Tree tree = new Tree();
        tree.setImmediate(false);
        tree.setWidth("100.0%");
        tree.setHeight("100.0%");
        return tree;
    }

    private VerticalLayout buildUsersLayout() {
        VerticalLayout userLayout = new VerticalLayout();
        userLayout.setImmediate(false);
        userLayout.setWidth("100.0%");
        userLayout.setHeight("100.0%");
        userLayout.setMargin(false);
        return usersLayout;
    }

    private HorizontalLayout createToolbar() {
        HorizontalLayout lo = new HorizontalLayout();
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
}
