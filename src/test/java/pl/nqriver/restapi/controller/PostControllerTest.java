package pl.nqriver.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.nqriver.restapi.model.Post;
import pl.nqriver.restapi.repository.PostRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    PostRepository postRepository;

    @Test
    @Transactional
    void shouldGetSinglePost() throws Exception {
        // given
        Post post = new Post();
        post.setTitle("Hello guys");
        post.setContent("Hello!!!");
        post.setCreated(LocalDateTime.now());
        postRepository.save(post);
        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/posts/" + post.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        // then
        Post retrievedPost = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Post.class);
        assertThat(retrievedPost).isNotNull();
        assertThat(retrievedPost.getId()).isEqualTo(post.getId());
        assertThat(retrievedPost.getTitle()).isEqualTo(post.getTitle());
        assertThat(retrievedPost.getContent()).isEqualTo(post.getContent());
    }
}