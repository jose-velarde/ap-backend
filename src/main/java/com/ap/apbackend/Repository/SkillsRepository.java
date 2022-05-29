package com.ap.apbackend.Repository;

import java.util.List;

import javax.transaction.Transactional;

import com.ap.apbackend.Model.Skills;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsRepository extends JpaRepository<Skills, Long> {
  List<Skills> findByProfileId(Long profileId);

  @Transactional
  void deleteByProfileId(long profileId);

}
