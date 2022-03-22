package pl.nqriver.restapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.nqriver.restapi.controller.dto.PostDto;
import pl.nqriver.restapi.model.Post;
import pl.nqriver.restapi.service.PostService;

import java.util.List;

import static pl.nqriver.restapi.controller.PostDtoMapper.mapToPostDtos;

@AllArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public List<PostDto> getPosts(@RequestParam(required = false) int page, Sort.Direction sort) {
        int pagetNumber = page < 0 ? 0 : page;
        return mapToPostDtos(postService.getAllPosts(pagetNumber, sort));
    }

    @GetMapping("/posts/comments")
    public List<Post> getPostsWithComments(@RequestParam(required = false) int page, Sort.Direction sort) {
        int pagetNumber = page < 0 ? 0 : page;
        return postService.getAllPostsWithComments(pagetNumber, sort);
    }

    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable Long id) {
        return postService.getPostById(id);
    }
}
