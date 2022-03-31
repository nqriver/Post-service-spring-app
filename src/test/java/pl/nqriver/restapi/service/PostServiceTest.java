package pl.nqriver.restapi.service;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.nqriver.restapi.model.Post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    @Transactional
    void shouldGetSinglePost() {
        // given
        // when
        Post singlePost = postService.getPostById(1L);
        // then
        assertThat(singlePost).isNotNull();
        assertThat(singlePost.getComments()).hasSize(9);
    }
}