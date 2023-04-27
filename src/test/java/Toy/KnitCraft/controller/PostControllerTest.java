package Toy.KnitCraft.controller;

import Toy.KnitCraft.domain.Post;
import Toy.KnitCraft.repository.PostRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("/posts 요청 시 DB에 값이 저장된다.")
    void test1() throws Exception {
        // when
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content("{\"title\": \"제목입니다.\", \"content\": \"내용입니다.\"}")
                )
                .andExpect(status().isOk())
                .andDo(print());
        // then
        assertEquals(1L, postRepository.count());

        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test2() throws Exception {
        // given
        Post post = Post.builder()
                .title("아이폰")
                .content("14")
                .build();
        postRepository.save(post);

        // expected
        mockMvc.perform(get("/posts/{postId}", post.getId())
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.title").value("아이폰"))
                .andExpect(jsonPath("$.content").value("14"))
                .andDo(print());
    }

    @Test
    @DisplayName("글 여러개 조회")
    void test3() throws Exception {
        // given
        Post post1 = Post.builder()
                .title("아이폰1")
                .content("구매1")
                .build();
        postRepository.save(post1);

        Post post2 = Post.builder()
                .title("아이폰2")
                .content("구매2")
                .build();
        postRepository.save(post2);

        // expected
        mockMvc.perform(get("/posts")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].id").value(post1.getId()))
                .andExpect(jsonPath("$[0].title").value("아이폰1"))
                .andExpect(jsonPath("$[0].content").value("구매1"))
                .andDo(print());

    }
}