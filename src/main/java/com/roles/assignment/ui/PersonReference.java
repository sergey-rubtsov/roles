package com.roles.assignment.ui;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;

import java.io.Serializable;
import java.util.*;

public class PersonReference implements Serializable, Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8580034519217252675L;
	private String personId;
	private Map<Object, Property> propertyMap;

	public PersonReference(String personId, Map<String, Object> personPropertyMap) {
		this.personId = personId;
		this.propertyMap = new HashMap<Object, Property>();
		for (Map.Entry<String, Object> entry : personPropertyMap.entrySet()) {
			this.propertyMap.put(entry.getKey(),
					new ObjectProperty(entry.getValue()));
		}
	}

	public String getPersonId() {
		return personId;
	}

	public Property getItemProperty(Object id) {
		return propertyMap.get(id);
	}

	public Collection<?> getItemPropertyIds() {
		return Collections.unmodifiableSet(propertyMap.keySet());
	}

	public boolean addItemProperty(Object id, Property property) {
		throw new UnsupportedOperationException("Item is read-only.");
	}

	public boolean removeItemProperty(Object id) {
		throw new UnsupportedOperationException("Item is read-only.");
	}
}