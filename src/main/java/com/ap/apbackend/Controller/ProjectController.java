package com.ap.apbackend.Controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.ap.apbackend.Exceptions.ProfileNotFoundException;
import com.ap.apbackend.Exceptions.ProjectNotFoundException;
import com.ap.apbackend.Model.Project;
import com.ap.apbackend.Repository.ProfileRepository;
import com.ap.apbackend.Repository.ProjectRepository;
import com.ap.apbackend.Service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ProjectController {
  ProjectService projectService;

  @Autowired
  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @GetMapping(value = "/project")
  public List<Project> getAllProjects() {
    return projectService.getAllProjects();
  }

  @GetMapping(value = "/project/{id}")
  public Project getProjectById(@PathVariable("id") @Min(1) Long id) {
    Project project = projectService.findById(id)
        .orElseThrow(() -> new ProjectNotFoundException("Project with " + id + " is Not Found!"));
    return project;
  }

  @PostMapping(value = "/project")
  public Project addProject(@Valid @RequestBody Project project) {
    return projectService.save(project);
  }

  @PutMapping(value = "/project/{id}")
  public Project updateProject(@PathVariable("id") @Min(1) Long id,
      @Valid @RequestBody Project newProject) {
    Project projectMatch = projectService.findById(id)
        .orElseThrow(() -> new ProjectNotFoundException("Project with " + id + " is Not Found!"));
    projectMatch.setProject_name(newProject.getProject_name());
    projectMatch.setProject_url(newProject.getProject_url());
    projectMatch.setRepository_url(newProject.getRepository_url());
    projectMatch.setDescription(newProject.getDescription());
    projectMatch.setStack(newProject.getStack());
    return projectService.save(projectMatch);
  }

  @DeleteMapping(value = "/project/{id}")
  public String deleteProject(@PathVariable("id") @Min(1) Long id) {
    Project project = projectService.findById(id)
        .orElseThrow(() -> new ProjectNotFoundException("Project with " + id + " is Not Found!"));
    projectService.deleteById(project.getId());
    return "Project with ID :" + id + " is deleted";
  }

  @Autowired
  private ProfileRepository profileRepository;

  @Autowired
  private ProjectRepository projectRepository;

  @GetMapping("/profiles/{profileId}/project")
  public ResponseEntity<List<Project>> getAllProjectsByProfileId(
      @PathVariable(value = "profileId") Long profileId) {
    if (!profileRepository.existsById(profileId)) {
      throw new ProfileNotFoundException("Not found Profile with id = " + profileId);
    }
    List<Project> projects = projectRepository.findByProfileId(profileId);
    return new ResponseEntity<>(projects, HttpStatus.OK);
  }

  @PostMapping("/profiles/{profileId}/project")
  public ResponseEntity<Project> createProject(@PathVariable(value = "profileId") Long profileId,
      @RequestBody Project projectRequest) {
    Project project = profileRepository.findById(profileId).map(profile -> {
      projectRequest.setProfile(profile);
      return projectRepository.save(projectRequest);
    }).orElseThrow(() -> new ProfileNotFoundException("Not found Profile with id = " + profileId));
    return new ResponseEntity<>(project, HttpStatus.CREATED);
  }

  @DeleteMapping("/profiles/{profileId}/project")
  public ResponseEntity<List<Project>> deleteAllProjectsOfProfile(
      @PathVariable(value = "profileId") Long profileId) {
    if (!profileRepository.existsById(profileId)) {
      throw new ProfileNotFoundException("Not found Profile with id = " + profileId);
    }
    projectRepository.deleteByProfileId(profileId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
