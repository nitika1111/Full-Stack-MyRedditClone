package com.nitika.myredditapp.dto;

import com.nitika.myredditapp.entity.VoteType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoteDto {
	private VoteType voteType;
	private Long postId;
}
