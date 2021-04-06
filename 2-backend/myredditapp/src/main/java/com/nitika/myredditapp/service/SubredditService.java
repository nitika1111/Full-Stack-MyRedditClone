package com.nitika.myredditapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nitika.myredditapp.dto.SubredditDto;
import com.nitika.myredditapp.entity.Subreddit;
import com.nitika.myredditapp.exception.MyRedditException;
import com.nitika.myredditapp.exception.SubredditNotFoundException;
import com.nitika.myredditapp.mapper.SubredditMapper;
import com.nitika.myredditapp.repository.SubredditRepository;

import lombok.AllArgsConstructor;

import static java.util.stream.Collectors.toList;
import static java.time.Instant.now;


@Service
@AllArgsConstructor
public class SubredditService {

	private final SubredditRepository subredditRepository;
	private final AuthService authService;
	private final SubredditMapper subredditMapper;
	
	@Transactional(readOnly=true)
	public List<SubredditDto> getAll() {
		
		return subredditRepository.findAll()
								  .stream()
								  .map(subredditMapper::mapSubredditToDto)
								  .collect(toList());
	}

	@Transactional(readOnly=true)
	public SubredditDto getSubreddit(Long id) {
		Subreddit subreddit= subredditRepository.findById(id)
					.orElseThrow(()->new SubredditNotFoundException("Subreddit not found for: "+id));
		return subredditMapper.mapSubredditToDto(subreddit);
	}

	@Transactional
	public SubredditDto save(SubredditDto subredditDto) {
		System.out.println("-------> Niti: Inside SubredditService: save()");

		Subreddit subreddit= subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto, authService.getCurrentUser()));
		subredditDto.setId(subreddit.getId());
        return subredditDto;
	}

	// commenting code below as we are using mapstruct mapper now!
	/*private Subreddit mapToSubreddit(SubredditDto subredditDto) {
		return Subreddit.builder()
				.name("/r/" + subredditDto.getName())
                .description(subredditDto.getDescription())
                .user(authService.getCurrentUser())
                .creationDate(now())
                .build();
	}

	private SubredditDto mapToDto(Subreddit subreddit) {
		return SubredditDto.builder().name(subreddit.getName())
                .id(subreddit.getId())
                .description(subreddit.getDescription())
                .postCount(subreddit.getPosts().size())
                .build();
		
	}*/

}


