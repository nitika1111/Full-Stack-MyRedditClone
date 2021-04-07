package com.nitika.myredditapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nitika.myredditapp.dto.CommentDto;
import com.nitika.myredditapp.entity.Comment;
import com.nitika.myredditapp.entity.Post;
import com.nitika.myredditapp.entity.User;

@Mapper(componentModel = "spring")
public interface CommentMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "user", source = "user")
    @Mapping(target = "text", source = "commentDto.text")
    @Mapping(target = "creationDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    Comment mapDtoToComment(CommentDto commentDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    CommentDto mapCommentToDto(Comment comment);
}
