package com.roles.assignment.domain;

//import org.apache.shiro.crypto.SecureRandomNumberGenerator;
//import org.apache.shiro.crypto.hash.Sha1Hash;
//import org.apache.shiro.util.ByteSource;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import org.hibernate.annotations.Parameter;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String id;

    @Version
    @Column(name = "version")
    private Integer version;

    @NotNull
    @Size(min = 1)
   /* @Pattern(regexp =
    "^[_A-Za-z0-9-]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$")*/
    private String email = "";

	@Column(unique=true, nullable=false)
    @Size(max = 32)
	private String login;

	@Column
    @Size(max = 32)
	private String password;

    @NotNull
    private boolean isLocked = false;

    @NotNull
    private boolean credentialsExpired = false;

    private byte[] passwordSalt;

    @ManyToMany
    @JoinTable(
            name="user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    protected List<Role> roles = new ArrayList<Role>();

    @OneToOne(fetch = FetchType.LAZY)
    private Person person;

    public User() {
    }

    public Set<String> getRoleSet() {
        Set<String> values = new HashSet<String>();
        //TODO: only for testing:
        if (roles.size() == 0) {
            values.add(SystemRoles.ROLE_USER.roleValue());
        }
        for (Role role : roles) {
            values.add(role.getRoleValue());
        }
        return values;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLocked() {
		return isLocked;
	}

    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    public void setPassword(char[] password) {
        this.setPassword(password.toString());
    }

    public char[] getCharPassword() {
        return this.getPassword().toCharArray();
    }

    public void setPasswordSalt(byte[] passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public byte[] getPasswordSalt() {
        return passwordSalt;
    }

    @Override
    public String toString() {
        return "User " + login;
    }

/*    public void setPassword(String password) {
        if (password != null && !password.equals(encodedPassword) && !"".equals(password)) {
            ByteSource saltSource = new SecureRandomNumberGenerator().nextBytes();
            this.passwordSalt = saltSource.getBytes();
            this.encodedPassword = new Sha1Hash(password, saltSource).toString();
        }
    }*/
}
