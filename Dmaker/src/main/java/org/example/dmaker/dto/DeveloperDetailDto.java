package org.example.dmaker.dto;

import lombok.*;
import org.example.dmaker.code.StatusCode;
import org.example.dmaker.entity.Developer;
import org.example.dmaker.type.DeveloperLevel;
import org.example.dmaker.type.DeveloperSkillType;

import java.util.function.Function;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeveloperDetailDto {
//    public static Function<? super Developer,?> fromEntity;
    private DeveloperLevel developerLevel;
    private DeveloperSkillType developerSkillType;
    private Integer experienceYears;
    private Long memberId;
    private StatusCode statusCode;

    private String name;
    private Integer age;

    public static DeveloperDetailDto fromEntity(Developer developer) {
        return DeveloperDetailDto.builder()
                .developerLevel(developer.getDeveloperLevel())
                .developerSkillType(developer.getDeveloperSkillType())
                .experienceYears(developer.getExperienceYears())
                .statusCode(developer.getStatusCode())
                .memberId(developer.getMemberID())
                .name(developer.getName())
                .age(developer.getAge())
                .build();
    }
}


