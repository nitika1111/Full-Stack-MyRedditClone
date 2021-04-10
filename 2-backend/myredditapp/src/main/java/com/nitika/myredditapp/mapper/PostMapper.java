package com.nitika.myredditapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.marlonlom.utilities.timeago.TimeAgo;

import com.nitika.myredditapp.dto.PostRequest;
import com.nitika.myredditapp.dto.PostResponse;
import com.nitika.myredditapp.entity.Post;
import com.nitika.myredditapp.entity.Subreddit;
import com.nitika.myredditapp.entity.User;
import com.nitika.myredditapp.entity.Vote;
import com.nitika.myredditapp.entity.VoteType;
import com.nitika.myredditapp.repository.CommentRepository;
import com.nitika.myredditapp.repository.VoteRepository;
import com.nitika.myredditapp.service.AuthService;
import static com.nitika.myredditapp.entity.VoteType.UPVOTE;

import java.util.Optional;

import static com.nitika.myredditapp.entity.VoteType.DOWNVOTE;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;

    @Mapping(target = "id", source = "postRequest.id")
    @Mapping(target = "postName", source = "postRequest.postName")
    @Mapping(target = "creationDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Post mapDtoToPost(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "post.id")
    @Mapping(target = "postName", source = "post.postName")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    @Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
    @Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
    public abstract PostResponse mapPostToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreationDate().toEpochMilli());
    }

    boolean isPostUpVoted(Post post) {
        return checkVoteType(post, UPVOTE);
    }

    boolean isPostDownVoted(Post post) {
        return checkVoteType(post, DOWNVOTE);
    }

    private boolean checkVoteType(Post post, VoteType voteType) {
        if (authService.isLoggedIn()) {
            Optional<Vote> voteForPostByUser =
                    voteRepository.findTopByPostAndUserOrderByIdDesc(post,
                            authService.getCurrentUser());
            //return voteForPostByUser.filter(vote -> vote.getType().equals(voteType)).isPresent();
            return voteForPostByUser.filter(vote -> vote.getType()==voteType.getDirection()).isPresent();
        }
        return false;
    }

}
