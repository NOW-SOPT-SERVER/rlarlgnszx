package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;



@WebMvcTest(Controller.class)
class ControllerTest {

    @Autowired
    MockMvc mvc;
    private TodoEntity expected;

    @MockBean
    TodoService todoService;


    @BeforeEach
    void setup() {

        this.expected = new TodoEntity();
        this.expected.setTitle("TEST TITLE");
        this.expected.setId(123L);
        this.expected.setOrder(0L);
        this.expected.setCompleted(false);

    }
    @Test
    void create() throws Exception {
        when(this.todoService.add(any(TodoRequest.class)))
                .then((i) -> {
                    TodoRequest request = i.getArgument(0, TodoRequest.class);
                    return new TodoEntity(
                            this.expected.getId(),
                            request.getTitle(),
                            this.expected.getOrder(),
                            this.expected.getCompleted());
                });
        TodoRequest request = new TodoRequest();
        request.setTitle("ANY TITLE");

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request);
        this.mvc.perform((RequestBuilder) post("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .clone(content)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("ANY TITLE"));
        
    }
    @Test
    void readOne() {
    }
}