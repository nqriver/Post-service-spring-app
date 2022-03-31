package pl.nqriver.restapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldLoginAndGetContent() throws Exception {
        MvcResult login =
                mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .content("{\"username\": \"test\", \"password\": \"test\"}"))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk())
                        .andReturn();

        String token = login.getResponse().getHeader("Authorization");

        mockMvc.perform(MockMvcRequestBuilders.get("/secured").header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string("secured"));
    }

    @Test
    void shouldNotAuthorizeWithoutToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/secured"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(401));
    }
}