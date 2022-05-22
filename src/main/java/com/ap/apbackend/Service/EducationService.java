package com.ap.apbackend.Service;

import java.util.List;
import java.util.Optional;

import com.ap.apbackend.Model.Education;
import com.ap.apbackend.Repository.EducationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService implements IEducationService {

  EducationRepository educationRepository;

  @Autowired
  public EducationService(EducationRepository educationRepository) {
    this.educationRepository = educationRepository;
  }

  @Override
  public List<Education> getAllEducations() {
    return educationRepository.findAll();
  };

  @Override
  public Optional<Education> findById(Long id) {
    return educationRepository.findById(id);
  }

  @Override
  public Education save(Education education) {
    return educationRepository.save(education);
  }

  @Override
  public void deleteById(Long id) {
    educationRepository.deleteById(id);
  }

}
