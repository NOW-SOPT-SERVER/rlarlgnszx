package server.sopt.week2.dto;

import server.sopt.week2.domain.Part;

public record MemberCreateDto(
        String name,
        Part part,
      int age)
{}
