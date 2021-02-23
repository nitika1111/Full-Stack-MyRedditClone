package com.nitika.myredditapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitika.myredditapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
}
