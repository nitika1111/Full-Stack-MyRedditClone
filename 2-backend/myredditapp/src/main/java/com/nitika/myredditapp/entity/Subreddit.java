package com.nitika.myredditapp.entity;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
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

import com.nitika.myredditapp.dto.SubredditDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subreddit")
public class Subreddit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

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
	private List<Post> posts;//= new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
}

















