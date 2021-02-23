package com.nitika.myredditapp.entity;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	//@NotBlank(message = "Date cannot be blank.")
	@Column(name = "creation_date")
	private Instant creationDate;
	
	@NotBlank(message = "Email ID cannot be blank.")
	@Column(name = "email")
	private String email;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@NotBlank(message = "Username cannot be blank.")
	@Column(name = "username")
	private String username;
	
	@NotBlank(message = "Password cannot be blank.")
	@Column(name = "password")
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Post> posts= new HashSet<>();
	
	
	
	
}























