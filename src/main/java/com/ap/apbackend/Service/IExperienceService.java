package com.ap.apbackend.Service;

import java.util.List;
import java.util.Optional;

import com.ap.apbackend.Model.Experience;

public interface IExperienceService {
  List<Experience> getAllExperiences();

  Optional<Experience> findById(Long id);

  Experience save(Experience experience);

  void deleteById(Long id);
}
