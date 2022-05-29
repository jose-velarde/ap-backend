package com.ap.apbackend.Service;

import java.util.List;
import java.util.Optional;

import com.ap.apbackend.Model.Skills;

public interface ISkillsService {
  List<Skills> getAllSkills();

  Optional<Skills> findById(Long id);

  Skills save(Skills skill);

  void deleteById(Long id);
}
