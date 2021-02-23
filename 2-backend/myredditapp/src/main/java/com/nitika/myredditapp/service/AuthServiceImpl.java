package com.nitika.myredditapp.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nitika.myredditapp.dto.RegisterRequest;
import com.nitika.myredditapp.entity.NotificationEmail;
import com.nitika.myredditapp.entity.User;
import com.nitika.myredditapp.entity.VerificationToken;
import com.nitika.myredditapp.exception.MyRedditException;
import com.nitika.myredditapp.repository.UserRepository;
import com.nitika.myredditapp.repository.VerificationTokenRepository;

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
	
	@Autowired
	public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
			MailContentBuilderServiceImpl mailContentBuilder, MailServiceImpl mailService,
			VerificationTokenRepository verificationTokenRepository) {
		
		this.userRepository= userRepository;
		this.passwordEncoder= passwordEncoder;
		this.mailContentBuilder= mailContentBuilder;
		this.mailService= mailService;
		this.verificationTokenRepository= verificationTokenRepository;
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
}


















