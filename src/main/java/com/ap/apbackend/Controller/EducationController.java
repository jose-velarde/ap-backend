package com.ap.apbackend.Controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.ap.apbackend.Exceptions.EducationNotFoundException;
import com.ap.apbackend.Exceptions.ProfileNotFoundException;
import com.ap.apbackend.Model.Education;
import com.ap.apbackend.Repository.EducationRepository;
import com.ap.apbackend.Repository.ProfileRepository;
import com.ap.apbackend.Service.EducationService;

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
public class EducationController {
  EducationService educationService;

  @Autowired
  public EducationController(EducationService educationService) {
    this.educationService = educationService;
  }

  @GetMapping(value = "/education")
  public List<Education> getAllEducations() {
    return educationService.getAllEducations();
  }

  @GetMapping(value = "/education/{id}")
  public Education getEducationById(@PathVariable("id") @Min(1) Long id) {
    Education education = educationService.findById(id)
        .orElseThrow(() -> new EducationNotFoundException("Education with " + id + " is Not Found!"));
    return education;
  }

  @PostMapping(value = "/education")
  public Education addEducation(@Valid @RequestBody Education education) {
    return educationService.save(education);
  }

  @PutMapping(value = "/education/{id}")
  public Education updateEducation(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody Education newEducation) {
    Education educationMatch = educationService.findById(id)
        .orElseThrow(() -> new EducationNotFoundException("Education with " + id + " is Not Found!"));
    educationMatch.setDegree(newEducation.getDegree());
    educationMatch.setInstitution(newEducation.getInstitution());
    educationMatch.setLocation(newEducation.getLocation());
    educationMatch.setInstitution_img_url(newEducation.getInstitution_img_url());
    educationMatch.setCareer(newEducation.getCareer());
    educationMatch.setGrade(newEducation.getGrade());
    educationMatch.setStart_date(newEducation.getStart_date());
    educationMatch.setEnd_date(newEducation.getEnd_date());
    return educationService.save(educationMatch);
  }

  @DeleteMapping(value = "/education/{id}")
  public String deleteEducation(@PathVariable("id") @Min(1) Long id) {
    Education education = educationService.findById(id)
        .orElseThrow(() -> new EducationNotFoundException("Education with " + id + " is Not Found!"));
    educationService.deleteById(education.getId());
    return "Education with ID :" + id + " is deleted";
  }

  @Autowired
  private ProfileRepository profileRepository;

  @Autowired
  private EducationRepository educationRepository;

  @GetMapping("/profiles/{profileId}/education")
  public ResponseEntity<List<Education>> getAllEducationsByProfileId(
      @PathVariable(value = "profileId") Long profileId) {
    if (!profileRepository.existsById(profileId)) {
      throw new ProfileNotFoundException("Not found Profile with id = " + profileId);
    }
    List<Education> educations = educationRepository.findByProfileId(profileId);
    return new ResponseEntity<>(educations, HttpStatus.OK);
  }

  @PostMapping("/profiles/{profileId}/education")
  public ResponseEntity<Education> createEducation(@PathVariable(value = "profileId") Long profileId,
      @RequestBody Education educationRequest) {
    Education education = profileRepository.findById(profileId).map(profile -> {
      educationRequest.setProfile(profile);
      return educationRepository.save(educationRequest);
    }).orElseThrow(() -> new ProfileNotFoundException("Not found Profile with id = " + profileId));
    return new ResponseEntity<>(education, HttpStatus.CREATED);
  }

  @DeleteMapping("/profiles/{profileId}/education")
  public ResponseEntity<List<Education>> deleteAllEducationsOfProfile(
      @PathVariable(value = "profileId") Long profileId) {
    if (!profileRepository.existsById(profileId)) {
      throw new ProfileNotFoundException("Not found Profile with id = " + profileId);
    }
    educationRepository.deleteByProfileId(profileId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
