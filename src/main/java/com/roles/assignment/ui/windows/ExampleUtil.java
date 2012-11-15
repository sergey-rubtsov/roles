package com.roles.assignment.ui.windows;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;

public final class ExampleUtil {
    public static IndexedContainer getContainer() {
        IndexedContainer c = new IndexedContainer();
        fillContainer(c);
        return c;
    }

    private static void fillContainer(IndexedContainer container) {
        final String[] array = new String[] { "20.12.2013", "Причина не известна", "21.12.2014", "Сгорел завод" };
        final Object date = "date";
        final Object cause = "cause";
        final Object today = "today";
        container.addContainerProperty(date, String.class,
                null);
        container.addContainerProperty(cause, String.class,
                null);
        container.addContainerProperty(today, String.class,
                null);
        for (int i = 0; i < array.length; i++) {
            String name = array[i++];
            String id = array[i];
            Item item = container.addItem(id);
            item.getItemProperty(date).setValue(name);
            item.getItemProperty(cause).setValue(id);
            item.getItemProperty(today).setValue("20.03.2013");
        }
    }
}

