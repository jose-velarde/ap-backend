package com.ap.apbackend.Repository;

import com.ap.apbackend.Model.Education;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

}
