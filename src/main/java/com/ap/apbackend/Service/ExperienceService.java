package com.ap.apbackend.Service;

import java.util.List;
import java.util.Optional;

import com.ap.apbackend.Model.Experience;
import com.ap.apbackend.Repository.ExperienceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService implements IExperienceService {

  ExperienceRepository experienceRepository;

  @Autowired
  public ExperienceService(ExperienceRepository experienceRepository) {
    this.experienceRepository = experienceRepository;
  }

  @Override
  public List<Experience> getAllExperiences() {
    return experienceRepository.findAll();
  };

  @Override
  public Optional<Experience> findById(Long id) {
    return experienceRepository.findById(id);
  }

  @Override
  public Experience save(Experience experience) {
    return experienceRepository.save(experience);
  }

  @Override
  public void deleteById(Long id) {
    experienceRepository.deleteById(id);
  }

}
