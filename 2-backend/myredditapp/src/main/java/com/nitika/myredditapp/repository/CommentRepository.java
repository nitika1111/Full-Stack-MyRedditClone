package com.nitika.myredditapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitika.myredditapp.entity.Comment;
import com.nitika.myredditapp.entity.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Integer> findByPost(Post post);

}
