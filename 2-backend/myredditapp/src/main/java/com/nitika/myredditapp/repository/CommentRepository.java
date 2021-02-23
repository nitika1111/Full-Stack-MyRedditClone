package com.nitika.myredditapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitika.myredditapp.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
