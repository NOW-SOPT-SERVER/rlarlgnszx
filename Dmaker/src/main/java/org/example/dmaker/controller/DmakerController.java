package org.example.dmaker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.example.dmaker.dto.CreateDeveleoper;
import org.example.dmaker.service.DmakerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

//사용자 입력 처음으로 받아지는 포인트
//ResponseBody를 달아주는것임 -> json으로 상정함
//REST API를 받는 객체로 쓰겟다
//내부에 Component로 Bean등록을함
@RestController
@Slf4j
@RequiredArgsConstructor
@ToString
public class DmakerController {
    private final DmakerService dmakerService;

    @GetMapping("/developers")
    public List<String> getAllDevelopers() {
        // Get /devleopers HTTP/1.1
        log.info("GET /developers HTTP/1.1");
        return Arrays.asList("Snow", "elsa", "kihoon");
    }
    @PostMapping("/create-developers")
    public CreateDeveleoper.Response createDeveloper(
            @Valid @RequestBody CreateDeveleoper.Request request
            ) {
        // Get /devleopers HTTP/1.1
        log.info("REQUEST : ", request);
        return dmakerService.createDevleoper(request);
//        return List.of("KIHOON");
    }


}
