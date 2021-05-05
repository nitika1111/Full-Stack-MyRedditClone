package com.nitika.myredditapp.service;

import static java.util.stream.Collectors.toList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nitika.myredditapp.dto.CommentDto;
import com.nitika.myredditapp.entity.Comment;
import com.nitika.myredditapp.entity.NotificationEmail;
import com.nitika.myredditapp.entity.Post;
import com.nitika.myredditapp.entity.User;
import com.nitika.myredditapp.exception.PostNotFoundException;
import com.nitika.myredditapp.exception.UsernameNotFoundException;
import com.nitika.myredditapp.mapper.CommentMapper;
import com.nitika.myredditapp.repository.CommentRepository;
import com.nitika.myredditapp.repository.PostRepository;
import com.nitika.myredditapp.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class CommentService {
	private static final String POST_URL = "";
	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	private final AuthService authService;
	private final UserRepository userRepository;
	private final CommentMapper commentMapper;
	private final MailContentBuilderService mailContentBuilderService;
	private final MailService mailService;
	
	public void saveComment(CommentDto commentDto) {
		Post post= postRepository.findById(commentDto.getPostId())
								 .orElseThrow(()-> new PostNotFoundException("Post not found for Post ID: "+commentDto.getPostId().toString()));
		Comment comment= commentMapper.mapDtoToComment(commentDto, post, authService.getCurrentUser());
		commentRepository.save(comment);
		
		// comment notification
		String message= mailContentBuilderService.build(post.getUser().getUsername()+
												" commented on your post."+ POST_URL);
		sendCommentNotification(message, post.getUser());
	}

	public List<CommentDto> getAllCommentsByPostId(Long postId) {
		Post post= postRepository.findById(postId)
				 .orElseThrow(()-> new PostNotFoundException("Post not found for Post ID: "+postId.toString()));
		System.out.println("-----> Niti: Inside getAllCommentsByPostId(Long postId)");
		System.out.println("-----> Niti: Post fetched: "+ post.toString());
		return commentRepository.findAllByPost(post)
								.stream()
								.map(commentMapper::mapCommentToDto)
								.collect(toList());
	}

	public List<CommentDto> getAllCommentsByUserName(String userName) {
		User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
		
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapCommentToDto)
                .collect(toList());
	}
	
	private void sendCommentNotification(String message, User user) {
		mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", 
												   user.getEmail(), 
												   message));
	}
}
