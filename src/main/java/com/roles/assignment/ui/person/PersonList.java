package com.roles.assignment.ui.person;

import com.roles.assignment.Widget;
import com.vaadin.ui.Table;

/**
 * User: RubtsovSL
 * Date: 04.12.12
 * Time: 13:31
 */
public class PersonList extends Table {

    public PersonList(final Widget widget) {
        setColumnCollapsingAllowed(true);
        setColumnReorderingAllowed(true);
        setSizeFull();

        setContainerDataSource(widget.getDataSource());
        setVisibleColumns(widget.getNaturalColOrder());
        setColumnHeaders(widget.getColHeadersEnglish());

        setSelectable(true);
        setImmediate(true);
        setNullSelectionAllowed(false);
    }
}
