package com.roles.assignment.repository;

import com.roles.assignment.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
    Role findByRoleLike(String roleName);
}
