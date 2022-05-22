package com.ap.apbackend.Repository;

import java.util.List;

import javax.transaction.Transactional;

import com.ap.apbackend.Model.Education;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
  List<Education> findByProfileId(Long profileId);

  @Transactional
  void deleteByProfileId(long profileId);

}
