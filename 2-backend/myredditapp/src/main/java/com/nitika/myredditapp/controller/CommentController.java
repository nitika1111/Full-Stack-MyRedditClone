package com.nitika.myredditapp.controller;

import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nitika.myredditapp.dto.CommentDto;
import com.nitika.myredditapp.service.CommentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/comments/")
@AllArgsConstructor
public class CommentController {
	private final CommentService commentService;
	
	@PostMapping
	public ResponseEntity<Void> saveComment(@RequestBody CommentDto commentDto){
		commentService.saveComment(commentDto);		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/by-post/{postId}")
	public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable Long postId){
		return status(OK).body(commentService.getAllCommentsByPostId(postId));
	}
	
	@GetMapping("/by-username/{userName}")
	public ResponseEntity<List<CommentDto>> getAllCommentsByUserName(@PathVariable String userName){
		return status(OK).body(commentService.getAllCommentsByUserName(userName));
	}

}















