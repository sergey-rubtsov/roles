package com.roles.assignment.repository;

import com.roles.assignment.domain.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

	List<Person> findByLastNameLike(String lastName, Pageable pageable);

    /**
     * Find all persons with the given lastname. This method will be translated into a query by constructing it directly
     * from the method name as there is no other query declared.
     *
     * @param lastName
     * @return
     */
    List<Person> findByLastName(String lastName);

    /**
     * Returns all persons with the given firstname. This method will be translated into a query using the one declared in
     * the {@link org.springframework.data.jpa.repository.Query} annotation declared one.
     *
     * @param firstName
     * @return
     */
    @Query("select p from Person p where p.firstName = ?1")
    List<Person> findByFirstName(String firstName);

}
