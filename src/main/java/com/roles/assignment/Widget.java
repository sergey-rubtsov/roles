package com.roles.assignment;

import com.roles.assignment.ui.ReferenceContainer;
import com.roles.assignment.ui.SearchFilter;

/**
 * User: RubtsovSL
 * Date: 15.10.12
 * Time: 15:40
 */
public interface Widget {

    public Object[] getNaturalColOrder();

    public String[] getColHeadersEnglish();

    public void search(SearchFilter searchFilter);

    ReferenceContainer getDataSource();

    void saveSearch(SearchFilter searchFilter);
}
