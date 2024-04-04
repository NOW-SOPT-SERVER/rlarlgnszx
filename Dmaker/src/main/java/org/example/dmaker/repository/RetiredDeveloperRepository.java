package org.example.dmaker.repository;

import org.example.dmaker.code.StatusCode;
import org.example.dmaker.entity.Developer;
import org.example.dmaker.entity.RetiredDeveloper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetiredDeveloperRepository extends JpaRepository<RetiredDeveloper,Long> {

}
