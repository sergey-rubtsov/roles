package com.roles.assignment.ui.person;

import com.roles.assignment.domain.Person;
import com.roles.assignment.service.PersonService;
import com.roles.assignment.ui.items.DataItem;
import com.roles.assignment.ui.items.PersonItem;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;

import java.beans.PropertyDescriptor;
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
    protected ArrayList<ItemSetChangeListener> listeners = new ArrayList<ItemSetChangeListener>();

    public static final Object[] NATURAL_COL_ORDER = new String[] {
            "firstName", "lastName"};
    protected static final Collection<Object> NATURAL_COL_ORDER_COLL = Collections
            .unmodifiableList(Arrays.asList(NATURAL_COL_ORDER));
    
    public PersonContainer(PersonService personService) {
        this.personService = personService;
        Person person = new Person();
        person.setFirstName("Santa");
        person.setLastName("Klaus");
        Person person2 = new Person();
        person2.setFirstName("Darth");
        person2.setLastName("Vader");
        this.personService.savePerson(person);
        this.personService.savePerson(person2);
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
        return NATURAL_COL_ORDER_COLL;
    }

    @Override
    public Collection<?> getItemIds() {
        return Collections.unmodifiableSet(itemsMap.keySet());
    }

    @Override
    public Property getContainerProperty(Object itemId, Object propertyId) {
        Item item = itemsMap.get(itemId);
        if (item != null) {
            return item.getItemProperty(propertyId);
        }
        return null;
    }

    @Override
    public Class<?> getType(Object propertyId) {
        try {
            // TODO Optimize, please!
            PropertyDescriptor pd = new PropertyDescriptor((String) propertyId, Person.class);
            return pd.getPropertyType();
        } catch (Exception e) {
            return null;
        }
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

    public synchronized void addListener(ItemSetChangeListener listener) {
        listeners.add(listener);
    }

    public synchronized void removeListener(ItemSetChangeListener listener) {
        listeners.remove(listener);
    }

    @SuppressWarnings("unchecked")
    protected void notifyListeners() {
        ArrayList<ItemSetChangeListener> cl = (ArrayList<ItemSetChangeListener>) listeners
                .clone();
        ItemSetChangeEvent event = new ItemSetChangeEvent() {

            public Container getContainer() {
                return PersonContainer.this;
            }
        };

        for (ItemSetChangeListener listener : cl) {
            listener.containerItemSetChange(event);
        }
    }
}
