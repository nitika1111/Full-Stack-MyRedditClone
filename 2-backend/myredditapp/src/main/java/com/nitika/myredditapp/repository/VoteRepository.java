package com.nitika.myredditapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitika.myredditapp.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
