package server.sopt.week2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import server.sopt.week2.dto.BlogCreateRequest;
import server.sopt.week2.repo.BlogRepostiory;
import server.sopt.week2.repo.MemberRepository;
import server.sopt.week2.service.BlogService;
import server.sopt.week2.service.MemberService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BlogController.class)
@AutoConfigureMockMvc
class BlogControllerTest {

    @SpyBean
    private BlogService blogService;

    @SpyBean
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private BlogRepostiory blogRepository;

    @Autowired
    private ObjectMapper objectMapper; //생성하는 객체를 String JSON 배열로 바꾸기 위해 사용

    @Autowired
    private MockMvc mockMvc;


    @Nested
    class createBlog {

        @Test
        @DisplayName("Blog 생성 실패 테스트")
        public void createBlogFail() throws Exception {
            //given
            String request = objectMapper.writeValueAsString(new BlogCreateRequest("소현이네 블로그", "블로그입니다."));
            System.out.println(blogRepository.findAll());
            //when
            mockMvc.perform(
                            post("/api/v1/blog")
                                    .content(request).header("memberId", 1)
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andDo(print());
        }
    }
}