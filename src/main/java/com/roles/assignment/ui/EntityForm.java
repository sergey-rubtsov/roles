package com.roles.assignment.ui;

import com.vaadin.ui.Component;

/**
 * User: RubtsovSL
 * Date: 29.11.12
 * Time: 12:03
 */
public interface EntityForm<T> extends Component {

    public void save();

    public T getEntity();

    public void discard();

}
