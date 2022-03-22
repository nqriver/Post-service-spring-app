package pl.nqriver.restapi.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.nqriver.restapi.model.Comment;
import pl.nqriver.restapi.model.Post;
import pl.nqriver.restapi.repository.CommentRepository;
import pl.nqriver.restapi.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostService {

    public static final int PAGE_SIZE = 5;

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    public List<Post> getAllPosts(int page, Sort.Direction sort) {
        return postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE,
                Sort.by(sort, "id")));
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow();
    }

    public List<Post> getAllPostsWithComments(int page, Sort.Direction sort) {
        List<Post> allPosts = postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id")));
        List<Long> postIds = allPosts.stream()
                .map(Post::getId)
                .collect(Collectors.toList());
        List<Comment> comments = commentRepository.findAllByPostIdIn(postIds);
        allPosts.forEach(post -> post.setComments(extractComments(comments, post.getId())));
        return allPosts;
    }

    private List<Comment> extractComments(List<Comment> comments, Long postId) {
        return comments.stream()
                .filter(comment -> comment.getPostId().equals(postId))
                .collect(Collectors.toList());
    }
}
