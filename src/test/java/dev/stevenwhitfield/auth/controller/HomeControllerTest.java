package dev.stevenwhitfield.auth.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import dev.stevenwhitfield.auth.config.SecurityConfig;
import dev.stevenwhitfield.auth.service.TokenService;

@WebMvcTest({HomeController.class, AuthController.class})
@Import({SecurityConfig.class, TokenService.class})
public class HomeControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void shouldReturnUnauthorizedWhenRequestingTokenWithoutAuth() throws Exception {
        this.mvc.perform(post("/api/auth/token")).andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturnTokenWithBasicAuth() throws Exception {
        MvcResult result =
                this.mvc.perform(post("/api/auth/token").with(httpBasic("user", "password")))
                        .andExpect(status().isOk()).andReturn();
        assertThat(result.getResponse().getContentAsString()).isNotEmpty();
    }

    @Test
    void shouldReturnStringFromHome() throws Exception {
        MvcResult result = this.mvc.perform(get("/")).andExpect(status().isOk()).andReturn();
        assertThat(result.getResponse().getContentAsString()).isNotEmpty();
    }

    @Test
    void shouldReturnUnauthorizedFromHelloWithoutAuth() throws Exception {
        this.mvc.perform(get("/hello")).andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturnHelloUserFromHelloWithAuth() throws Exception {
        MvcResult result =
                this.mvc.perform(post("/api/auth/token").with(httpBasic("user", "password")))
                        .andExpect(status().isOk()).andReturn();
        String token = result.getResponse().getContentAsString();

        this.mvc.perform(get("/hello").header("Authorization", "Bearer " + token))
                .andExpect(content().string("Hello user"));
    }

    @Test
    @WithMockUser
    void shouldReturnOKFromHelloWithMockUser() throws Exception {
        this.mvc.perform(get("/hello")).andExpect(status().isOk());
    }

}
