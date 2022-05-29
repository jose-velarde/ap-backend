package com.ap.apbackend.Service;

import java.util.List;
import java.util.Optional;

import com.ap.apbackend.Model.Skills;
import com.ap.apbackend.Repository.SkillsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillsService implements ISkillsService {

  SkillsRepository skillsRepository;

  @Autowired
  public SkillsService(SkillsRepository skillsRepository) {
    this.skillsRepository = skillsRepository;
  }

  @Override
  public List<Skills> getAllSkills() {
    return skillsRepository.findAll();
  };

  @Override
  public Optional<Skills> findById(Long id) {
    return skillsRepository.findById(id);
  }

  @Override
  public Skills save(Skills skill) {
    return skillsRepository.save(skill);
  }

  @Override
  public void deleteById(Long id) {
    skillsRepository.deleteById(id);
  }

}
