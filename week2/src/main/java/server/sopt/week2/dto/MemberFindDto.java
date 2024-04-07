package server.sopt.week2.dto;

import server.sopt.week2.domain.Member;
import server.sopt.week2.domain.Part;

public record MemberFindDto(
        String name, Part part, int age
        ) {
    public static MemberFindDto of(Member member){
        return new MemberFindDto(member.getName(),member.getPart(),member.getAge());
    }
}
