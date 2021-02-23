package com.nitika.myredditapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitika.myredditapp.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
