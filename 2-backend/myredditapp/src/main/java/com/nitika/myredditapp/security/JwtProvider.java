package com.nitika.myredditapp.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;

import javax.annotation.PostConstruct;
import java.security.cert.CertificateException;
import java.sql.Date;
import java.time.Instant;
import static java.util.Date.from;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.base.ParserBase;
import com.nitika.myredditapp.exception.MyRedditException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import static io.jsonwebtoken.Jwts.parser;

@Service
public class JwtProvider {
	
	private KeyStore keyStore;
	
	@Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;
	
	@PostConstruct
	public void init() {
		try {
			keyStore= KeyStore.getInstance("JKS");
			InputStream resourceAsStream= getClass().getResourceAsStream("/myredditapp.jks");
			
			keyStore.load(resourceAsStream, "secret".toCharArray());
			
		} catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
			throw new MyRedditException("Exception occurred while loading keystore");
		}
	}

	public String generateToken(Authentication authenticate) {
		System.out.println("-------> Niti: Inside JwtProvider--->generateToken()");
		org.springframework.security.core.userdetails.User principal= 
															(User) authenticate.getPrincipal();
		System.out.println("-------> Niti: Inside JwtProvider--->generateToken()-->principal= "+principal.toString());

		return Jwts.builder().setSubject(principal.getUsername())
							 .setIssuedAt(from(Instant.now()))
							 .signWith(getPrivateKey())
							 .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
							 .compact();
	}
	
	public String generateTokenWithUserName(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

	private PrivateKey getPrivateKey() {

		try {
			PrivateKey privateKey= (PrivateKey) keyStore.getKey("myreddit", "secret".toCharArray());
			
			System.out.println("-------> Niti: Inside JwtProvider--->getPrivateKey()--->private key="+privateKey);
			return privateKey;
			
		} catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
            throw new MyRedditException("Exception occured while retrieving private key from keystore");

		}
	}

	public boolean validateJwt(String jwt) {
        parser().setSigningKey(getPublickey()).parseClaimsJws(jwt);
        return true;
    }

    private PublicKey getPublickey() {
        try {
            return keyStore.getCertificate("myreddit").getPublicKey();
        } catch (KeyStoreException e) {
            throw new MyRedditException("Exception occured while retrieving "
            		+ "public key from keystore");
        }
    }

    public String getUsernameFromJwt(String jwt) {
        Claims claims = parser()
                .setSigningKey(getPublickey())
                .parseClaimsJws(jwt)
                .getBody();

        return claims.getSubject();
    }
	
    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }
    
}















