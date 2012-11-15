package com.roles.assignment.ui;

import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class HelpWindow extends Window {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7763501491080666228L;
	private static final String HELP_HTML_SNIPPET = "This is "
			+ "an application built during <strong><a href=\""
			+ "https://assignment.com/tutorial/\">Vaadin</a></strong> "
			+ "tutorial. Hopefully it doesn't need any real help.";

	public HelpWindow() {
		setCaption("Help");
		addComponent(new Label(HELP_HTML_SNIPPET, Label.CONTENT_XHTML));
	}

}