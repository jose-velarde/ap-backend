package com.ap.apbackend.Repository;

import java.util.List;

import javax.transaction.Transactional;

import com.ap.apbackend.Model.Experience;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
  List<Experience> findByProfileId(Long profileId);
  
  @Transactional
  void deleteByProfileId(long profileId);

}
