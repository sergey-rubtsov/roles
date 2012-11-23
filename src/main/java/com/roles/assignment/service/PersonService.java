package com.roles.assignment.service;

import com.roles.assignment.domain.Person;
import com.roles.assignment.ui.persons.QueryMetaData;

import java.util.List;

public interface PersonService {

	public abstract long countAllPeople();

	public abstract void deletePerson(Person person);

	public abstract Person findPerson(String id);

    public List<Person> findByLastNameSortByFirstNameLike(String lastName, int first, int max, int offset);

	public abstract List<Person> findAllPeople();

	public abstract Person savePerson(Person person);

	public abstract List<Person> findPersonEntries(QueryMetaData queryMetaData);

}
