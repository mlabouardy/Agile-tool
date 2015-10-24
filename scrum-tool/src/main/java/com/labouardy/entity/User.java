package com.labouardy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

import com.labouardy.annotations.UniqueEmail;

@Entity
public class User {

	@Id @GeneratedValue
	private int id;
	
	@Size(min=3, message="First name must be at least 3 characters")
	private String firstname;
	
	@Size(min=3, message="Last name must be at least 3 characters")
	private String lastname;
	
	@Email(message="Not an email")
	@Column(unique=true)
	@UniqueEmail(message="Email already used !")
	private String email;
	
	@Size(min=6, message="Password must be at least 6 characters")
	private String password;
	
	@ManyToOne
	private Role role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
