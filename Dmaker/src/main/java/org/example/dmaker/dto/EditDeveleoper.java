package org.example.dmaker.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.dmaker.entity.Developer;
import org.example.dmaker.type.DeveloperLevel;
import org.example.dmaker.type.DeveloperSkillType;

public class EditDeveleoper {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        @NotNull//-> 꼭 필요한 정보
        private DeveloperLevel developerLevel;
        @NotNull
        private DeveloperSkillType developerSkillType;
        @NotNull
        @Min(-1)
        @Max(20)
        private Integer experienceYears;

        @NotNull
        @Size(min=3,max=50,message= "Name is 3~50")
        private String name;

        @NotNull
//        @Size(min=3,max=50,message= "memberID is 3~50")
        private Long memberId;

        @Min(19)
        private Integer age;

    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private DeveloperLevel developerLevel;
        private DeveloperSkillType developerSkillType;
        private Integer experienceYears;
        private Long memberId;

        public static Response fromEntity(Developer developer) {
            return Response.builder()
                    .developerLevel(developer.getDeveloperLevel())
                    .developerSkillType(developer.getDeveloperSkillType())
                    .experienceYears(developer.getExperienceYears())
                    .memberId(developer.getMemberID())
                    .build();
        }
    }
}
