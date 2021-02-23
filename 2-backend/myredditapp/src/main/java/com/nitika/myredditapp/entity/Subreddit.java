package com.nitika.myredditapp.entity;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subreddit")
public class Subreddit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank(message = "Date cannot be blank.")
	@Column(name = "creation_date")
	private Instant creationDate;
	
	@NotBlank(message = "Description cannot be blank.")
	@Lob
	@Column(name = "description")
	private String description;
	
	@Column(name = "name")
	@NotBlank(message = "Subreddit Name cannot be blank.")
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subreddit")
	private Set<Post> posts= new HashSet<>();
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	

}

















