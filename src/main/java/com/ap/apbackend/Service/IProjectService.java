package com.ap.apbackend.Service;

import java.util.List;
import java.util.Optional;

import com.ap.apbackend.Model.Project;

public interface IProjectService {
  List<Project> getAllProjects();

  Optional<Project> findById(Long id);

  Project save(Project project);

  void deleteById(Long id);
}
