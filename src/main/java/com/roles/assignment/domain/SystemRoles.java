package com.roles.assignment.domain;

/**
 * User: RubtsovSL
 * Date: 15.10.12
 * Time: 8:32
 */
public enum SystemRoles {

    ROLE_USER("user"), ROLE_ADMIN("admin"), ROLE_FOUNDER("founder");

    private String roleValue;

    SystemRoles(String roleValue) {
        this.roleValue = roleValue;
    }

    public String roleValue() {
        return roleValue;
    }
}
