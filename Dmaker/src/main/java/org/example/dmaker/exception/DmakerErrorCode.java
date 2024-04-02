package org.example.dmaker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DmakerErrorCode {
    NO_DEVELOPER("해당 개발자 없음"),
    DUPLICATED_MEMBER_ID("ID 중복 "),
    LEVEL_EXP_YEAR_NOT_MATCH("개발레벨과 연차 맞지 않음"),
    INTERNAL_SERVER_ERROR("서버 오류"),
    INVALID_REQEUST("요청 에러"),;
    private final String description;
}

