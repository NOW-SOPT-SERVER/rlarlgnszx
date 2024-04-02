package org.example.dmake.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DevDtoTest {

    @Test
    void test(){
        final DevDto devDto = DevDto.builder()
                .name("name")
                .name("name2")
                .age(21)
                .startAt(LocalDateTime.now())
                .build();
        System.out.println(devDto);
        devDto.printLog();
    }
}