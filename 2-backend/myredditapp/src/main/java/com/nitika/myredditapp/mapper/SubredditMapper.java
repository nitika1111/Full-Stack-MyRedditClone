package com.nitika.myredditapp.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.nitika.myredditapp.dto.SubredditDto;
import com.nitika.myredditapp.entity.Post;
import com.nitika.myredditapp.entity.Subreddit;
import com.nitika.myredditapp.entity.User;

@Mapper(componentModel = "spring")
public interface SubredditMapper {
		
	@Mapping(target = "postCount",
			 expression = "java(mapPosts(subreddit.getPosts()))")
	SubredditDto mapSubredditToDto(Subreddit subreddit);
	
	default Integer mapPosts(List<Post> numberOfPosts) {
		return numberOfPosts.size();
	}

	@Mapping(target = "id", source = "subredditDto.id")
	@Mapping(target = "description", source = "subredditDto.description")
	@Mapping(target = "creationDate", expression = "java(java.time.Instant.now())")
	@Mapping(target = "posts", ignore=true)
	@Mapping(target = "user", source = "user")
	@Mapping(target = "name", qualifiedByName = "name")
	Subreddit mapDtoToSubreddit(SubredditDto subredditDto, User user);

	@Named("name")
	default String createSubredditName(String subredditName) { 
		return "/r/"+	subredditName; 
	}
	 
}







