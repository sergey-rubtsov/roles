package com.roles.assignment.ui.persons;

import com.roles.assignment.Widget;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class SearchView extends Panel {

	private TextField tf;
	private NativeSelect fieldToSearch;
	private CheckBox saveSearch;
	private TextField searchName;
	private Widget app;

	public SearchView(final Widget app) {
		this.app = app;

		setCaption("Search contacts");
		setSizeFull();

		/* Use a FormLayout as main layout for this Panel */
		FormLayout formLayout = new FormLayout();
		setContent(formLayout);

		/* Create UI components */
		tf = new TextField("Search value");
		fieldToSearch = new NativeSelect("Search by");
		saveSearch = new CheckBox("Save search");
		saveSearch.setImmediate(true);
		saveSearch.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				searchName.setVisible(event.getButton().booleanValue());
			}
		});
		searchName = new TextField("Save as...");
		Button search = new Button("Search");
		search.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				performSearch();
			}
		});

		/* Initialize fieldToSearch */
		for (int i = 0; i < app.getNaturalColOrder().length; i++) {
			fieldToSearch.addItem(app.getNaturalColOrder()[i]);
			fieldToSearch.setItemCaption(app.getNaturalColOrder()[i],
					app.getColHeadersEnglish()[i]);
		}

		fieldToSearch.setValue("lastName");
		fieldToSearch.setNullSelectionAllowed(false);

		/* Initialize save checkbox */
		saveSearch.setValue(true);

		/* Add all the created components to the form */
		addComponent(fieldToSearch);
		addComponent(tf);
		addComponent(saveSearch);
		addComponent(searchName);
		addComponent(search);
	}

	private void performSearch() {
		String searchTerm = (String) tf.getValue();
		SearchFilter searchFilter = new SearchFilter(fieldToSearch.getValue(),
				searchTerm, (String) searchName.getValue());
		if (saveSearch.booleanValue()) {
			app.saveSearch(searchFilter);
		}
		app.search(searchFilter);
	}
}