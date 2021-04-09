package com.nitika.myredditapp.entity;

import java.util.Arrays;

import com.nitika.myredditapp.exception.MyRedditException;

public enum VoteType {
     DOWNVOTE(-1), UPVOTE(1);

    private final int direction;

    VoteType(int direction) {
    	this.direction= direction;
    }
    
    public Integer getDirection() {
        return direction;
    }
    
	/*
	 * public static VoteType lookup(Integer direction) { return
	 * Arrays.stream(VoteType.values()) .filter(value ->
	 * value.getDirection().equals(direction)) .findAny() .orElseThrow(() -> new
	 * MyRedditException("Vote not found")); }
	 */
}