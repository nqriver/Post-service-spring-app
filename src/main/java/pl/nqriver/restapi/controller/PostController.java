package pl.nqriver.restapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
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
    public List<PostDto> getPosts(@RequestParam(required = false) Integer page, Sort.Direction sort,
                                  UsernamePasswordAuthenticationToken user) {
        int pagetNumber = page == null || page < 0 ? 0 : page;
        Sort.Direction sortDir = sort != null ? sort : Sort.Direction.ASC;
        return mapToPostDtos(postService.getAllPosts(pagetNumber, sortDir));
    }

    @GetMapping("/posts/comments")
    public List<Post> getPostsWithComments(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pagetNumber = page == null || page < 0 ? 0 : page;
        return postService.getAllPostsWithComments(pagetNumber, sort);
    }

    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable Long id) {
        return postService.getPostById(id);
    }


    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post) {
        return postService.addPost(post);
    }

    @PutMapping("/posts")
    public Post editPost(@RequestBody Post post) {
        return postService.editPost(post);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(Long id) {
        postService.deletePostById(id);
    }

}
