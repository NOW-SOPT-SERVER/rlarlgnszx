package org.example.dmaker.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeveloperSkillType {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드"),
    FULLSTACK("풀스택");
    private final String description;
}
