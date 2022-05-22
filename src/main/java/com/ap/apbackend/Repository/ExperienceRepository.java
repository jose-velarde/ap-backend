package com.ap.apbackend.Repository;

import com.ap.apbackend.Model.Experience;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {

}
