package com.roles.assignment.ui.persons;

import com.roles.assignment.Widget;
import com.vaadin.data.Property;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.roles.assignment.Widget;

public class PersonList extends Table {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5269799790226554688L;

	public PersonList(final Widget main) {
		setColumnCollapsingAllowed(true);
		setColumnReorderingAllowed(true);
		setSizeFull();
		// customize email column to have mailto: links using column generator
		addGeneratedColumn("email", new ColumnGenerator() {
			public Component generateCell(Table source, Object itemId, Object columnId) {
				String email = (String) getContainerProperty(itemId, "email").getValue();
				Link l = new Link();
				l.setResource(new ExternalResource("mailto:" + email));
				l.setCaption(email);
				return l;
			}
		});
		setContainerDataSource(main.getDataSource());
		setVisibleColumns(main.getNaturalColOrder());
		setColumnHeaders(main.getColHeadersEnglish());
		/*
		 * Make table selectable, react immediatedly to user events, and pass
		 * events to the controller (our main application)
		 */
		setSelectable(true);
		setImmediate(true);
		addListener((Property.ValueChangeListener) main);
		/* We don't want to allow persons to de-select a row */
		setNullSelectionAllowed(false);
	}
}