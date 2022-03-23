package pl.nqriver.restapi.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.nqriver.restapi.model.Comment;
import pl.nqriver.restapi.model.Post;
import pl.nqriver.restapi.repository.CommentRepository;
import pl.nqriver.restapi.repository.PostRepository;

import javax.transaction.Transactional;
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

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post editPost(Post post) {
        Post editedPost = postRepository.findById(post.getId()).orElseThrow();
        editedPost.setTitle(post.getTitle());
        editedPost.setContent(post.getContent());
//        return postRepository.save(post); //not necessary since Hibernate detects Entity change and saves changed entity
        return post;
    }

    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }
}
