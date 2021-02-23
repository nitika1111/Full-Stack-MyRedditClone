package com.nitika.myredditapp.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "token")
public class VerificationToken {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
	
	@Column(name = "expiry_date")
    private Instant expiryDate;
    
	@Column(name = "token")
    private String token;
    
    @OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
   
}
