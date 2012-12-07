package com.roles.assignment;

import com.roles.assignment.ui.persons.SearchFilter;
import com.vaadin.data.Container;

/**
 * User: RubtsovSL
 * Date: 15.10.12
 * Time: 15:40
 */
public interface Widget {

    public Object[] getNaturalColOrder();

    public String[] getColHeadersEnglish();

    public void search(SearchFilter searchFilter);

    Container getDataSource();

    void saveSearch(SearchFilter searchFilter);
}
