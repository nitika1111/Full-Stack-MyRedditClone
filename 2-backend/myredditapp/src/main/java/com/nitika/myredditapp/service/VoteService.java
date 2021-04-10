package com.nitika.myredditapp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nitika.myredditapp.dto.VoteDto;
import com.nitika.myredditapp.entity.Post;
import com.nitika.myredditapp.entity.Vote;
import com.nitika.myredditapp.entity.VoteType;
import com.nitika.myredditapp.exception.MyRedditException;
import com.nitika.myredditapp.exception.PostNotFoundException;
import com.nitika.myredditapp.repository.PostRepository;
import com.nitika.myredditapp.repository.VoteRepository;
import static com.nitika.myredditapp.entity.VoteType.UPVOTE;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class VoteService {
	
    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    @Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser = 
        		voteRepository.findTopByPostAndUserOrderByIdDesc(post, 
        														 authService.getCurrentUser());
		/*
		 * if (voteByPostAndUser.isPresent() && voteByPostAndUser.get().getType()
		 * .equals(voteDto.getVoteType())) { throw new
		 * MyRedditException("You have already " + voteDto.getVoteType() +
		 * "'d for this post"); }
		 */
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getType()== voteDto.getVoteType().getDirection()) {
            throw new MyRedditException("You have already "
                    + voteDto.getVoteType() + "'d for this post");
        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(mapDtoToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapDtoToVote(VoteDto voteDto, Post post) {
    	VoteType theVoteType= voteDto.getVoteType();//.getDirection();//getVoteType();//.valueOf(VoteType, name);
    	int voteTypeInt= theVoteType.getDirection();
    	System.out.println("-----> Niti: "+ voteTypeInt);
        return Vote.builder()
                //.type(voteDto.getVoteType())
        		.type(voteTypeInt)
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
	
}
