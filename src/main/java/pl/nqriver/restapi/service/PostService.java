package pl.nqriver.restapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.nqriver.restapi.model.Post;
import pl.nqriver.restapi.repository.PostRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow();
    }
}
