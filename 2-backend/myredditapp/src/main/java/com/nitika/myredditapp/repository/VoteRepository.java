package com.nitika.myredditapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitika.myredditapp.entity.Post;
import com.nitika.myredditapp.entity.User;
import com.nitika.myredditapp.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

	Optional<Vote> findTopByPostAndUserOrderByIdDesc(Post post, User currentUser);

}
