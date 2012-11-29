package com.roles.assignment.domain;

import com.roles.assignment.domain.document.Document;
import com.roles.assignment.domain.document.Signature;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String id;

    @Version
    @Column(name = "version")
    private Integer version;

	@NotNull
	@Size(min = 1)
	private String firstName = "";

	@NotNull
	@Size(min = 1)
	private String lastName = "";

	// @Pattern(regexp =
	// "^((\\+)?[1-9]{1,2})?([-\\s\\.])?((\\(\\d{1,4}\\))|\\d{1,4})(([-\\s\\.])?[0-9]{1,12}){1,2}$")
	private String phoneNumber = "";

	@Size(max = 80)
	private String streetAddress = "";

	@Max(99999L)
	private Integer postalCode = 99999;

	@Size(max = 20)
	private String city = "";

    @Temporal(TemporalType.DATE)
    @Column(name="birthdate")
    private Date birthdate;

    @OneToMany
    private Set<Document> documents;

    @OneToMany
    private Set<Signature> signatures;

    public Person() {
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public Integer getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
