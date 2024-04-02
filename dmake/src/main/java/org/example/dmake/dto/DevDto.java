package org.example.dmake.dto;

import lombok.*;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString//? -> toString method구현
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Slf4j
@Data//위 어노테이ㄴ션 집합
//@UtilityClass

public class DevDto {
    @NonNull
    String name;
    @NonNull
    Integer age;
    LocalDateTime startAt;

    public void printLog(){
        log.info(getName());
    }
}
