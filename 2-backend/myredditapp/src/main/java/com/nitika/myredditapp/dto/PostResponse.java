package com.nitika.myredditapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
	private Long id;
	private String postName;
	private String subredditName;
	private String url;
	private String description;
	private String userName;
	private Integer commentCount;
	private String duration;
	private Boolean upVote; 
	private Boolean downVote;

}