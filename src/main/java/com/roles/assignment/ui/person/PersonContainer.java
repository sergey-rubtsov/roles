package com.roles.assignment.ui.person;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;

import java.util.Collection;

/**
 * User: RubtsovSL
 * Date: 04.12.12
 * Time: 14:36
 */
public class PersonContainer implements Container {

    public PersonContainer() {
    }

    @Override
    public Item getItem(Object itemId) {
        return null;  //create me
    }

    @Override
    public Collection<?> getContainerPropertyIds() {
        return null;  //create me
    }

    @Override
    public Collection<?> getItemIds() {
        return null;  //create me
    }

    @Override
    public Property getContainerProperty(Object itemId, Object propertyId) {
        return null;  //create me
    }

    @Override
    public Class<?> getType(Object propertyId) {
        return null;  //create me
    }

    @Override
    public int size() {
        return 0;  //create me
    }

    @Override
    public boolean containsId(Object itemId) {
        return false;  //create me
    }

    @Override
    public Item addItem(Object itemId) throws UnsupportedOperationException {
        return null;  //create me
    }

    @Override
    public Object addItem() throws UnsupportedOperationException {
        return null;  //create me
    }

    @Override
    public boolean removeItem(Object itemId) throws UnsupportedOperationException {
        return false;  //create me
    }

    @Override
    public boolean addContainerProperty(Object propertyId, Class<?> type, Object defaultValue) throws UnsupportedOperationException {
        return false;  //create me
    }

    @Override
    public boolean removeContainerProperty(Object propertyId) throws UnsupportedOperationException {
        return false;  //create me
    }

    @Override
    public boolean removeAllItems() throws UnsupportedOperationException {
        return false;  //create me
    }
}
