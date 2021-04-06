package com.nitika.myredditapp.service;


import java.util.Collection;
import static java.util.Collections.singletonList;

import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nitika.myredditapp.entity.User;
import com.nitika.myredditapp.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;	
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) 
					   throws UsernameNotFoundException {
		System.out.println("-------> Niti: Inside UserDetailsServiceImpl->loadUserByUsername()");		

		Optional<User> userOptional= userRepository.findByUsername(username);
		
		User user= userOptional.orElseThrow(()->
								new UsernameNotFoundException(
									username + "not found in DB!"));
		System.out.println("-------> Niti: Inside UserDetailsServiceImpl->fetched User:"+ user.toString());		

		return new org.springframework.security
                .core.userdetails.User(	user.getUsername(), 
                						user.getPassword(),
                						user.isEnabled(), 
                						true, 
                						true,
                						true, 
                						getAuthorities("USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }

}










