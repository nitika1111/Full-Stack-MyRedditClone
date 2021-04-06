package com.nitika.myredditapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitika.myredditapp.entity.Post;
import com.nitika.myredditapp.entity.Subreddit;
import com.nitika.myredditapp.entity.User;

public interface PostRepository extends JpaRepository<Post, Long> {

	Optional<List<Post>> findAllBySubreddit(Subreddit subreddit);

	Optional<List<Post>> findAllByUser(User user);

}
