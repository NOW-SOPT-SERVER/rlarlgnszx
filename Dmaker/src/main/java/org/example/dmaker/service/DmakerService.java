package org.example.dmaker.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dmaker.code.StatusCode;
import org.example.dmaker.dto.CreateDeveleoper;
import org.example.dmaker.dto.DeveloperDetailDto;
import org.example.dmaker.dto.DeveloperDto;
import org.example.dmaker.dto.EditDeveleoper;
import org.example.dmaker.entity.Developer;
import org.example.dmaker.entity.RetiredDeveloper;
import org.example.dmaker.exception.DmakerErrorCode;
import org.example.dmaker.exception.DmakerException;
import org.example.dmaker.repository.DeveloperRepository;
import org.example.dmaker.repository.RetiredDeveloperRepository;
import org.example.dmaker.type.DeveloperLevel;
import org.example.dmaker.type.DeveloperSkillType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DmakerService {
    private final DeveloperRepository developerRepository;

    private final RetiredDeveloperRepository retiredDeveloperRepository;
    
    
//    ACID 법칙을 지켜서 Transactional해야함
//    Atomic
//    Consistency
//    Isolation
//    Durability -> 모든 이력 남기기 -> DB 오류 해결할때?



    @Transactional
    public CreateDeveleoper.Response createDevleoper(CreateDeveleoper.Request request) {
        Developer developer = Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYears(request.getExperienceYears())
                .name(request.getName())
                .age(request.getAge())
                .statusCode(StatusCode.EMPLOYED)
                .memberID(request.getMemberId())
                .build();
        developerRepository.save(developer);
        return CreateDeveleoper.Response.fromEntity(developer);
    }

    private void validateCreateDeveloperRequest(CreateDeveleoper.Request request) {
        if (request.getDeveloperLevel() == DeveloperLevel.SENIOR && request.getExperienceYears() < 10) {
                throw new DmakerException(DmakerErrorCode.LEVEL_EXP_YEAR_NOT_MATCH);
        }
        if (request.getDeveloperLevel() == DeveloperLevel.JUNGNIOR && (request.getExperienceYears() < 4 || request.getExperienceYears() > 10)) {
            throw new DmakerException(DmakerErrorCode.LEVEL_EXP_YEAR_NOT_MATCH);
        }
        if (request.getDeveloperLevel() == DeveloperLevel.JUNIOR && request.getExperienceYears() > 4) {
            throw new DmakerException(DmakerErrorCode.LEVEL_EXP_YEAR_NOT_MATCH);
        }
        developerRepository.findById(request.getMemberId())
                .ifPresent((cur_developer -> {
                    throw new DmakerException(DmakerErrorCode.NO_DEVELOPER);
                }));
    }
    public List<DeveloperDto> getAllDeveloper () {
        return developerRepository.findDeveloperByStatusCodeEquals(StatusCode.EMPLOYED)
                .stream().map(DeveloperDto::fromEntity)
                .collect(Collectors.toList());
    }

    public DeveloperDetailDto getdetailDeveloper(Long memberId) {
        return developerRepository.findById(memberId)
                .map(DeveloperDetailDto::fromEntity)
                .orElseThrow(() -> new DmakerException(DmakerErrorCode.NO_DEVELOPER));
    }

    @Transactional
    public DeveloperDetailDto editDeveloper(Long memberId, EditDeveleoper.@Valid Request request) {
        validateEditDeveloperRequest(request);
        Developer developer = developerRepository.findById(memberId)
                .orElseThrow(() ->  new DmakerException(DmakerErrorCode.NO_DEVELOPER));
        developer.setDeveloperLevel(request.getDeveloperLevel());
        developer.setDeveloperSkillType(request.getDeveloperSkillType());
        developer.setExperienceYears(request.getExperienceYears());
        return DeveloperDetailDto.fromEntity(developer);
    }

    private void validateEditDeveloperRequest(EditDeveleoper.Request request) {
        DeveloperSkillType developerSkillType = request.getDeveloperSkillType();
        DeveloperLevel developerLevel = request.getDeveloperLevel();
        if (request.getDeveloperLevel() == DeveloperLevel.SENIOR && request.getExperienceYears() < 10) {
            throw new DmakerException(DmakerErrorCode.LEVEL_EXP_YEAR_NOT_MATCH);
        }
        if (request.getDeveloperLevel() == DeveloperLevel.JUNGNIOR && (request.getExperienceYears() < 4 || request.getExperienceYears() > 10)) {
            throw new DmakerException(DmakerErrorCode.LEVEL_EXP_YEAR_NOT_MATCH);
        }
        if (request.getDeveloperLevel() == DeveloperLevel.JUNIOR && request.getExperienceYears() > 4) {
            throw new DmakerException(DmakerErrorCode.LEVEL_EXP_YEAR_NOT_MATCH);
        }

    }

    @Transactional
    public DeveloperDetailDto deleteDeveloper(Long memberId) {
//        Employ -> RETIRED

        Developer developer =  developerRepository.findById(memberId)
                .orElseThrow(() -> new DmakerException(DmakerErrorCode.NO_DEVELOPER));
        developer.setStatusCode(StatusCode.RETIRED);

        //        SAVE IN REPOSIOTRY
        RetiredDeveloper retiredDeveloper = RetiredDeveloper.builder()
                .memberID(developer.getMemberID())
                .name(developer.getName())
                .build();
        retiredDeveloperRepository.save(retiredDeveloper);
        return DeveloperDetailDto.fromEntity(developer);

    }
}

