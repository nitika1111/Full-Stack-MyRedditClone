package com.nitika.myredditapp.controller;

import java.util.List;
import static org.springframework.http.ResponseEntity.status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitika.myredditapp.dto.PostRequest;
import com.nitika.myredditapp.dto.PostResponse;
import com.nitika.myredditapp.service.PostService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
	private final PostService postService;
	
	@PostMapping
	public ResponseEntity<Void> savePost(@RequestBody PostRequest postRequest) {
		System.out.println("-------> Niti: Inside PostController -> savePost");
		postService.save(postRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	// get ALL Posts
	@GetMapping
	public ResponseEntity<List<PostResponse>> getAllPosts() {
		System.out.println("-------> Niti: Inside PostController-> getAllPosts");

		return status(HttpStatus.OK).body(postService.getAllPosts());
	}

	// get Posts by POST ID
	@GetMapping("/{id}")
	public ResponseEntity<PostResponse> getPost(@PathVariable Long id ) {
		System.out.println("-------> Niti: Inside PostController -> getPost");

		return status(HttpStatus.OK).body(postService.getPostById(id));
	}
	
	// get Posts by SUBREDDIT ID
	@GetMapping("/by-subreddit/{id}")
	public ResponseEntity<List<PostResponse>> getPostsBySubreddit(@PathVariable Long id) {
		System.out.println("-------> Niti: Inside PostController -> getPostsBySubreddit");

		return status(HttpStatus.OK).body(postService.getPostsBySubreddit(id));
	}
	
	// get Posts by USER ID
	@GetMapping("/by-user/{username}")
	public ResponseEntity<List<PostResponse>> getAllPosts(@PathVariable String username) {
		System.out.println("-------> Niti: Inside PostController -> getAllPosts");

		return status(HttpStatus.OK).body(postService.getPostsByUsername(username));
	}
	
}















