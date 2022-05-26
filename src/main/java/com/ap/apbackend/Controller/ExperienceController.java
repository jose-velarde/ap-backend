package com.ap.apbackend.Controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.ap.apbackend.Exceptions.ExperienceNotFoundException;
import com.ap.apbackend.Model.Experience;
import com.ap.apbackend.Service.ExperienceService;

import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin
@RequestMapping("/api")
public class ExperienceController {
  ExperienceService experienceService;

  @Autowired
  public ExperienceController(ExperienceService experienceService) {
    this.experienceService = experienceService;
  }

  @GetMapping(value = "/experience")
  public List<Experience> getAllExperiences() {
    return experienceService.getAllExperiences();
  }

  @GetMapping(value = "/experience/{id}")
  public Experience getExperienceById(@PathVariable("id") @Min(1) Long id) {
    Experience experience = experienceService.findById(id)
        .orElseThrow(() -> new ExperienceNotFoundException("Experience with " + id + " is Not Found!"));
    return experience;
  }

  @PostMapping(value = "/experience")
  public Experience addExperience(@Valid @RequestBody Experience experience) {
    return experienceService.save(experience);
  }

  @PutMapping(value = "/experience/{id}")
  public Experience updateExperience(@PathVariable("id") @Min(1) Long id,
      @Valid @RequestBody Experience newExperience) {
    Experience experienceMatch = experienceService.findById(id)
        .orElseThrow(() -> new ExperienceNotFoundException("Experience with " + id + " is Not Found!"));
    experienceMatch.setCompany(newExperience.getCompany());
    experienceMatch.setCompany(newExperience.getCompany());
    experienceMatch.setLocation(newExperience.getLocation());
    experienceMatch.setPosition(newExperience.getPosition());
    experienceMatch.setPosition(newExperience.getPosition());
    experienceMatch.setPosition(newExperience.getPosition());
    return experienceService.save(experienceMatch);
  }

  @DeleteMapping(value = "/experience/{id}")
  public String deleteExperience(@PathVariable("id") @Min(1) Long id) {
    Experience experience = experienceService.findById(id)
        .orElseThrow(() -> new ExperienceNotFoundException("Experience with " + id + " is Not Found!"));
    experienceService.deleteById(experience.getId());
    return "Experience with ID :" + id + " is deleted";
  }
}
