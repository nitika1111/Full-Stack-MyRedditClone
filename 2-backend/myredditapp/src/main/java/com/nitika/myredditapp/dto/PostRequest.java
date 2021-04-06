package com.nitika.myredditapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
	private Long id;
	private String postName;
	private String subredditName;
	private String userName;
	private String url;
	private String description;
}
