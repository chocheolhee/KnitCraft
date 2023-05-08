package Toy.KnitCraft.controller;

import Toy.KnitCraft.domain.Address;
import Toy.KnitCraft.domain.Member;
import Toy.KnitCraft.repository.MemberRepository;
import Toy.KnitCraft.repository.PostRepository;
import Toy.KnitCraft.repository.SessionRepository;
import Toy.KnitCraft.request.Login;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SessionRepository sessionRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void clean() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("로그인 성공")
    void test1() throws Exception {
        // given
        Address address = Address.builder()
                .city("고양시")
                .street("지축동")
                .zipcode("10333")
                .build();
        memberRepository.save(Member.builder()
                .email("cheolhee@gmail.com")
                .password("1234")
                .username("조철희")
                .address(address)
                .build());

        Login login = Login.builder()
                .email("cheolhee@gmail.com")
                .password("1234")
                .build();

        String json = objectMapper.writeValueAsString(login);

        // expected
        mockMvc.perform(post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    @DisplayName("로그인 성공 후 세션 1개 생성")
    void test2() throws Exception {
        // given
        Address address = Address.builder()
                .city("고양시")
                .street("지축동")
                .zipcode("10333")
                .build();
        Member member = memberRepository.save(Member.builder()
                .email("cheolhee@gmail.com")
                .password("1234")
                .username("조철희")
                .address(address)
                .build());

        Login login = Login.builder()
                .email("cheolhee@gmail.com")
                .password("1234")
                .build();

        String json = objectMapper.writeValueAsString(login);

        // expected
        mockMvc.perform(post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());

        assertEquals(1L, member.getSessions().size());

    }
}