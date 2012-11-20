package com.roles.assignment.repository;

import com.roles.assignment.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    /**
     * Find the user with the given login. This method will be translated into a query using the
     * {@link javax.persistence.NamedQuery} annotation at the {@link User} class.
     *
     * @param login
     * @return
     */
    User findByLoginLike(String login);
}
