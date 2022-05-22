package com.ap.apbackend.Service;

import java.util.List;
import java.util.Optional;

import com.ap.apbackend.Model.Education;

public interface IEducationService {
  List<Education> getAllEducations();

  Optional<Education> findById(Long id);

  Education save(Education education);

  void deleteById(Long id);
}
