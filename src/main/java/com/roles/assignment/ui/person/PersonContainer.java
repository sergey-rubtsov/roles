package com.roles.assignment.ui.person;

import com.roles.assignment.domain.Person;
import com.roles.assignment.service.PersonService;
import com.roles.assignment.ui.items.DataItem;
import com.roles.assignment.ui.items.PersonItem;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;

import java.util.*;

/**
 * User: RubtsovSL
 * Date: 04.12.12
 * Time: 14:36
 */
public class PersonContainer implements Container {

    private List<DataItem> itemList;
    private PersonService personService;
    private Map<Object, DataItem> itemsMap;
    
    public PersonContainer(PersonService personService) {
        this.personService = personService;
        this.itemList = new ArrayList<DataItem>();
    }

    public void refresh() {
        List<Person> persons = personService.findAllPeople();
        for (Person person : persons) {
            itemList.add(new PersonItem(person));
        }
        itemsMap = new HashMap<Object, DataItem>(itemList.size());
        for (DataItem pi : itemList) {
            itemsMap.put(pi.getId(), pi);
        }
    }

    @Override
    public Item getItem(Object itemId) {
        return itemsMap.get(itemId);
    }

    @Override
    public Collection<?> getContainerPropertyIds() {
        return null;  //create me
    }

    @Override
    public Collection<?> getItemIds() {
        return Collections.unmodifiableSet(itemsMap.keySet());
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
        if (itemList == null) return 0;
        return itemList.size();
    }

    @Override
    public boolean containsId(Object itemId) {
        return itemsMap.containsKey(itemId);
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
