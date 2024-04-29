package server.sopt.week2.dto.member;

import jakarta.validation.constraints.Size;
import server.sopt.week2.domain.Part;

public record MemberCreateDto(
        @Size(max = 6, message = "최대 글자 6글자")
        String name,
        Part part,
      int age)
{}
