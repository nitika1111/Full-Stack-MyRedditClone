package com.nitika.myredditapp.entity;

import java.time.Instant;
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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
public class Post {
	
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
	
	@Column(name = "title")
	@NotBlank(message = "Title cannot be blank.")
	private String title;
	
	@NotBlank(message = "URL cannot be blank.")
	@Column(name = "url")
	private String url;
	
	@Column(name = "vote_count")
	private Integer voteCount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subreddit_id")
	private Subreddit subreddit;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public Instant getCreatedDate() {
		// TODO Auto-generated method stub
		return null;
	}
	

}

















