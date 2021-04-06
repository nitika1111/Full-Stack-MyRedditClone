package com.nitika.myredditapp.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nitika.myredditapp.dto.AuthenticationResponse;
import com.nitika.myredditapp.dto.LoginRequest;
import com.nitika.myredditapp.dto.RegisterRequest;
import com.nitika.myredditapp.entity.NotificationEmail;
import com.nitika.myredditapp.entity.User;
import com.nitika.myredditapp.entity.VerificationToken;
import com.nitika.myredditapp.exception.MyRedditException;
import com.nitika.myredditapp.exception.UsernameNotFoundException;
import com.nitika.myredditapp.repository.UserRepository;
import com.nitika.myredditapp.repository.VerificationTokenRepository;
import com.nitika.myredditapp.security.JwtProvider;

import static java.time.Instant.now;

import java.util.Optional;
import java.util.UUID;

import com.nitika.myredditapp.utils.Constants;

@Service
@Transactional
public class AuthServiceImpl implements AuthService{
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private VerificationTokenRepository verificationTokenRepository;
	private MailContentBuilderServiceImpl mailContentBuilder;
	private MailServiceImpl mailService;
	private AuthenticationManager authenticationManager;
	private JwtProvider jwtProvider;
	
	@Autowired
	public AuthServiceImpl(	UserRepository userRepository, 
							PasswordEncoder passwordEncoder,
							MailContentBuilderServiceImpl mailContentBuilder, 
							MailServiceImpl mailService,
							VerificationTokenRepository verificationTokenRepository,
							AuthenticationManager authenticationManager,
							JwtProvider jwtProvider) {
		
		this.userRepository= userRepository;
		this.passwordEncoder= passwordEncoder;
		this.mailContentBuilder= mailContentBuilder;
		this.mailService= mailService;
		this.verificationTokenRepository= verificationTokenRepository;
		this.authenticationManager= authenticationManager;
		this.jwtProvider= jwtProvider;
	}
	
	@Override
	@Transactional
	public void signup(RegisterRequest registerRequest) {
		System.out.println("-------> Niti: Inside AuthService->signup()");

		User user= new User();
		
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(encodePassword(registerRequest.getPassword()));	
		user.setEnabled(false);
		user.setCreationDate(now());
		System.out.println("-------> Niti: Inside AuthService->signup()--> Going to save user: "+user.toString());

		userRepository.save(user);
		
		System.out.println("-------> Niti: Inside AuthService->signup()--> user saved");

		System.out.println("-------> Niti: Inside AuthService->signup()--> calling generateVerificationToken(user)");
		String token= generateVerificationToken(user);
		System.out.println("-------> Niti: Inside AuthService->signup()--> returned from generateVerificationToken(user)");
		System.out.println("-------> Niti: Inside AuthService->signup()--> token: "+token);
		
		// create email body
		String message= mailContentBuilder.build("Thank you for signing up to My Reddit App. "
				+ "To Activate your account, please click the URL below: "
				+ Constants.ACTIVATION_EMAIL + "/" + token);
		System.out.println("-------> Niti: Inside AuthService->signup()--> created message: "+message);

		// send email
		mailService.sendMail(new NotificationEmail("Please Activate your MyReddit Account",
													user.getEmail(),
													message));
		System.out.println("-------> Niti: Inside AuthService->signup()--> mail sent!");

		
	}

	private String generateVerificationToken(User user) {
		System.out.println("-------> Niti: Inside AuthService->generateVerificationToken()--> user: "+user.toString());

		VerificationToken verificationToken= new VerificationToken();		
		verificationToken.setUser(user);
		verificationToken.setToken(UUID.randomUUID().toString());
		System.out.println("-------> Niti: Inside AuthService->generateVerificationToken()--> saving verificationToken(): "+verificationToken.toString());		
		verificationTokenRepository.save(verificationToken);
		System.out.println("-------> Niti: Inside AuthService->generateVerificationToken()--> saved verificationToken(): "+verificationToken.toString());		

		return verificationToken.getToken();
	}

	private String encodePassword(String password) {
		
		return passwordEncoder.encode(password);
	}

	public void verifyAccount(String token) {
		System.out.println("-------> Niti: Inside AuthService->verifyAccount()");		

		Optional<VerificationToken> verificationTokenOptional=
				verificationTokenRepository.findByToken(token);
		System.out.println("-------> Niti: Inside AuthService->generateVerificationToken()--> fetching token using Optional"+ verificationTokenOptional.toString());		

		verificationTokenOptional.orElseThrow(()->new MyRedditException("Invalid token!"));
		
		System.out.println("-------> Niti: Inside AuthService->generateVerificationToken()--> calling fetchAndEnable()");		
		fetchAndEnableUser(verificationTokenOptional.get());
		System.out.println("-------> Niti: Inside AuthService->generateVerificationToken()--> returned from fetchAndEnable()");		

	}

	@Transactional
	private void fetchAndEnableUser(VerificationToken verificationToken) {

		String username= verificationToken.getUser().getUsername();
		
		User user= userRepository.findByUsername(username).orElseThrow(()-> new MyRedditException("User not found with ID "+ username));
		user.setEnabled(true);
		userRepository.save(user);
	}

	@Override
	public AuthenticationResponse login(LoginRequest loginRequest) {
		System.out.println("-------> Niti: Inside AuthService--->login()");

		Authentication authenticate= authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
														loginRequest.getPassword()));
		System.out.println("-------> Niti: Inside AuthService--->calling setAuthentication(authenticate)");
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		
		System.out.println("-------> Niti: Inside AuthService--->calling generateToken(authenticate)");		
		String authenticationToken= jwtProvider.generateToken(authenticate);
		
		return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
	}

	@Override
	@Transactional(readOnly = true)
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = 
        		(org.springframework.security.core.userdetails.User) SecurityContextHolder
        															.getContext()
        															.getAuthentication()
        															.getPrincipal();
        
        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(
                		() -> new UsernameNotFoundException("User name not found - " 
                										+ principal.getUsername()));
    }

	@Override
	public boolean isLoggedIn() {
		// TODO Auto-generated method stub
		return false;
	}
}


















