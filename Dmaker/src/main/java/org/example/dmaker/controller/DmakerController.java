package org.example.dmaker.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.example.dmaker.dto.CreateDeveleoper;
import org.example.dmaker.dto.DeveloperDetailDto;
import org.example.dmaker.dto.DeveloperDto;
import org.example.dmaker.dto.EditDeveleoper;
import org.example.dmaker.service.DmakerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public final static Integer me = 10;

    @GetMapping("/developers")
    public List<DeveloperDto> getAllDevelopers() {
        // Get /devleopers HTTP/1.1
        log.info("GET /developers HTTP/1.1");
        return dmakerService.getAllDeveloper();
    }

    @GetMapping("/{memberId}")
    public DeveloperDetailDto getDeveloperDetail(
            @PathVariable Long memberId
    ) {
        log.info("GET /developers HTTP/1.1");
        return dmakerService.getdetailDeveloper(memberId);

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

    @PutMapping("/update/{memberId}")
    public DeveloperDetailDto editDeveloper(
            @PathVariable Long memberId,
            @Valid @RequestBody EditDeveleoper.Request request
    ) {
        log.info("GET /developers HTTP/1.1");
        return dmakerService.editDeveloper(memberId, request);
    }

    @Transactional
    @DeleteMapping("/delete/{memberId}")
    public DeveloperDetailDto deleteDeveloper(
            @PathVariable Long memberId
    ) {
        return dmakerService.deleteDeveloper(memberId);
    }
}