package org.example.dmaker.repository;

import org.example.dmaker.code.StatusCode;
import org.example.dmaker.entity.Developer;
import org.example.dmaker.entity.RetiredDeveloper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer,Long> {
    List<Developer> findDeveloperByStatusCodeEquals(StatusCode statusCode);
//    Optional<Object> findAll(StatusCode statusCode);
}
