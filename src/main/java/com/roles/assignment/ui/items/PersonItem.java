package com.roles.assignment.ui.items;

import com.roles.assignment.domain.Person;
import com.vaadin.data.Property;

import java.io.Serializable;
import java.util.Collection;

/**
 * User: RubtsovSL
 * Date: 07.12.12
 * Time: 14:13
 */
public class PersonItem implements Serializable, DataItem {

    //private String personId;
    //private Map<Object, Property> propertyMap;
    private Person person;

    public PersonItem(Person person) {
        this.person = person;
    }

    public PersonItem() {
    }

    @Override
    public String getId() {
        return person.getId();
    }

    @Override
    public Property getItemProperty(Object id) {
        return null;  //create me
        //return propertyMap.get(id);
    }

    @Override
    public Collection<?> getItemPropertyIds() {
        return null;  //create me
        //return Collections.unmodifiableSet(propertyMap.keySet());
    }

    @Override
    public boolean addItemProperty(Object id, Property property) throws UnsupportedOperationException {
        return false;  //create me
    }

    @Override
    public boolean removeItemProperty(Object id) throws UnsupportedOperationException {
        return false;  //create me
    }
}
