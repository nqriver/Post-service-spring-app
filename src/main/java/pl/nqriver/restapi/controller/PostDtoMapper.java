package pl.nqriver.restapi.controller;

import pl.nqriver.restapi.controller.dto.PostDto;
import pl.nqriver.restapi.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostDtoMapper {

    private PostDtoMapper() {
    }

    public static List<PostDto> mapToPostDtos(List<Post> allPosts) {
        return allPosts.stream()
                .map(PostDtoMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    public static PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .created(post.getCreated())
                .build();
    }
}
