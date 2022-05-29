package com.ap.apbackend.Controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.ap.apbackend.Exceptions.ExperienceNotFoundException;
import com.ap.apbackend.Exceptions.ProfileNotFoundException;
import com.ap.apbackend.Model.Experience;
import com.ap.apbackend.Repository.ExperienceRepository;
import com.ap.apbackend.Repository.ProfileRepository;
import com.ap.apbackend.Service.ExperienceService;

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
    experienceMatch.setCompany_img_url(newExperience.getCompany_img_url());
    experienceMatch.setLocation(newExperience.getLocation());
    experienceMatch.setStart_date(newExperience.getStart_date());
    experienceMatch.setEnd_date(newExperience.getEnd_date());
    experienceMatch.setAchievements(newExperience.getAchievements());
    return experienceService.save(experienceMatch);
  }

  @DeleteMapping(value = "/experience/{id}")
  public String deleteExperience(@PathVariable("id") @Min(1) Long id) {
    Experience experience = experienceService.findById(id)
        .orElseThrow(() -> new ExperienceNotFoundException("Experience with " + id + " is Not Found!"));
    experienceService.deleteById(experience.getId());
    return "Experience with ID :" + id + " is deleted";
  }

  @Autowired
  private ProfileRepository profileRepository;

  @Autowired
  private ExperienceRepository experienceRepository;

  @GetMapping("/profiles/{profileId}/experience")
  public ResponseEntity<List<Experience>> getAllExperiencesByProfileId(
      @PathVariable(value = "profileId") Long profileId) {
    if (!profileRepository.existsById(profileId)) {
      throw new ProfileNotFoundException("Not found Profile with id = " + profileId);
    }
    List<Experience> experiences = experienceRepository.findByProfileId(profileId);
    return new ResponseEntity<>(experiences, HttpStatus.OK);
  }

  @PostMapping("/profiles/{profileId}/experience")
  public ResponseEntity<Experience> createExperience(@PathVariable(value = "profileId") Long profileId,
      @RequestBody Experience experienceRequest) {
    Experience experience = profileRepository.findById(profileId).map(profile -> {
      experienceRequest.setProfile(profile);
      return experienceRepository.save(experienceRequest);
    }).orElseThrow(() -> new ProfileNotFoundException("Not found Profile with id = " + profileId));
    return new ResponseEntity<>(experience, HttpStatus.CREATED);
  }

  @DeleteMapping("/profiles/{profileId}/experience")
  public ResponseEntity<List<Experience>> deleteAllExperiencesOfProfile(
      @PathVariable(value = "profileId") Long profileId) {
    if (!profileRepository.existsById(profileId)) {
      throw new ProfileNotFoundException("Not found Profile with id = " + profileId);
    }
    experienceRepository.deleteByProfileId(profileId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
