package com.nitika.myredditapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitika.myredditapp.entity.Subreddit;

public interface SubredditRepository extends JpaRepository<Subreddit, Long> {

}
