package com.nitika.myredditapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitika.myredditapp.dto.SubredditDto;
import com.nitika.myredditapp.service.SubredditService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
public class SubredditController {
	private SubredditService subredditService;
	
	@GetMapping
	public List<SubredditDto> getAllSubreddits() {
        return subredditService.getAllSubreddits();
    }
	
	@GetMapping("/{id}")
	public SubredditDto getSubreddit(@PathVariable Long id) {
        return subredditService.getSubredditById(id);
    }
	
	@PostMapping()
	public SubredditDto getSubreddit(@RequestBody @Valid SubredditDto subredditDto) {
		System.out.println("-------> Niti: Inside SubredditController: getSubreddit()");

        return subredditService.save(subredditDto);
    }

}













