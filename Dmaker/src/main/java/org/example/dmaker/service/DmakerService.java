package org.example.dmaker.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dmaker.dto.CreateDeveleoper;
import org.example.dmaker.entity.Developer;
import org.example.dmaker.exception.DmakerErrorCode;
import org.example.dmaker.exception.DmakerException;
import org.example.dmaker.repository.DeveloperRepository;
import org.example.dmaker.type.DeveloperLevel;
import org.example.dmaker.type.DeveloperSkillType;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DmakerService {
    private final DeveloperRepository developerRepository;

//    ACID 법칙을 지켜서 Transactional해야함
//    Atomic
//    Consistency
//    Isolation
//    Durability -> 모든 이력 남기기 -> DB 오류 해결할때?

    private final EntityManager em;

    @Transactional
    public CreateDeveleoper.Response createDevleoper(CreateDeveleoper.Request request) {
        Developer developer = Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYears(request.getExperienceYears())
                .name(request.getName())
                .age(request.getAge())
                .memberID(request.getMemberId())
                .build();

        developerRepository.save(developer);
        return CreateDeveleoper.Response.fromEntity(developer);
    }

    private void validateCreateDeveloperRequest(CreateDeveleoper.Request request) {
        if(request.getDeveloperLevel()==DeveloperLevel.SENIOR && request.getExperienceYears() < 10 ){
            throw new DmakerException(DmakerErrorCode.LEVEL_EXP_YEAR_NOT_MATCH);
        }
        if(request.getDeveloperLevel()== DeveloperLevel.JUNGNIOR && (request.getExperienceYears() < 4 || request.getExperienceYears()>10 )){
            throw new DmakerException(DmakerErrorCode.LEVEL_EXP_YEAR_NOT_MATCH);
        }
        if(request.getDeveloperLevel()== DeveloperLevel.JUNIOR&& request.getExperienceYears() >  4 ){
            throw new DmakerException(DmakerErrorCode.LEVEL_EXP_YEAR_NOT_MATCH);
        }

        developerRepository.findById(request.getMemberId())
                .ifPresent((cur_developer -> {
                    throw new DmakerException(DmakerErrorCode.NO_DEVELOPER);
                }));

    }
}
