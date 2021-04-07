package com.nitika.myredditapp.service;

import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nitika.myredditapp.dto.PostRequest;
import com.nitika.myredditapp.dto.PostResponse;
import com.nitika.myredditapp.entity.Post;
import com.nitika.myredditapp.entity.Subreddit;
import com.nitika.myredditapp.entity.User;
import com.nitika.myredditapp.exception.PostNotFoundException;
import com.nitika.myredditapp.exception.SubredditNotFoundException;
import com.nitika.myredditapp.exception.UsernameNotFoundException;
import com.nitika.myredditapp.mapper.PostMapper;
import com.nitika.myredditapp.repository.PostRepository;
import com.nitika.myredditapp.repository.SubredditRepository;
import com.nitika.myredditapp.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class PostService {
	
	private final PostRepository postRepository;
	private final SubredditRepository subredditRepository;
	private final AuthService authService;
	private final PostMapper postMapper;
	private final UserRepository userRepository;

	
	// Get all posts
	@Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
		System.out.println("-------> Niti: Inside PostService -> getAllPosts");

        return postRepository.findAll()
                .stream()
                .map(postMapper::mapPostToDto)
                .collect(toList());
    }
	
	// Get post by Post ID
	@Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
		System.out.println("-------> Niti: Inside PostService -> getPostsById");

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapPostToDto(post);
    }

	// Get post by Subreddit ID
    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
		System.out.println("-------> Niti: Inside PostService -> getPostsBySubreddit");

        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException("Subreddit not found for Subreddit ID: "+subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit)
        		.orElseThrow(() -> new PostNotFoundException("Posts not found for Subreddit ID: "+subredditId.toString()));
        return posts
        		.stream()
        		.map(postMapper::mapPostToDto)
        		.collect(toList());
    }
    
	// Get post by ID
    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
		System.out.println("-------> Niti: Inside PostService -> getPostsByUsername");

		User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found for Username: "+username));
		List<Post> posts= postRepository.findAllByUser(user)
        		.orElseThrow(() -> new PostNotFoundException("Posts not found for Username: "+username));

		return posts
				.stream()
                .map(postMapper::mapPostToDto)
                .collect(toList());
    }
    
	// Save post
    public void save(PostRequest postRequest) {
		System.out.println("-------> Niti: Inside PostService -> save");

        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        postRepository.save(postMapper.mapDtoToPost(postRequest, subreddit, authService.getCurrentUser()));
    }
}
