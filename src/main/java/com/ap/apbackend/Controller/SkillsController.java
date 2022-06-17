package com.ap.apbackend.Controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.ap.apbackend.Exceptions.ProfileNotFoundException;
import com.ap.apbackend.Exceptions.SkillsNotFoundException;
import com.ap.apbackend.Model.Skills;
import com.ap.apbackend.Repository.ProfileRepository;
import com.ap.apbackend.Repository.SkillsRepository;
import com.ap.apbackend.Service.SkillsService;

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
public class SkillsController {
  SkillsService skillsService;

  @Autowired
  public SkillsController(SkillsService skillsService) {
    this.skillsService = skillsService;
  }

  @GetMapping(value = "/skills")
  public List<Skills> getAllSkills() {
    return skillsService.getAllSkills();
  }

  @GetMapping(value = "/skills/{id}")
  public Skills getSkillsById(@PathVariable("id") @Min(1) Long id) {
    Skills skills = skillsService.findById(id)
        .orElseThrow(() -> new SkillsNotFoundException("Skills with " + id + " is Not Found!"));
    return skills;
  }

  @PutMapping(value = "/skills/{id}")
  public Skills updateSkills(@PathVariable("id") @Min(1) Long id,
      @Valid @RequestBody Skills newSkills) {
    Skills skillsMatch = skillsService.findById(id)
        .orElseThrow(() -> new SkillsNotFoundException("Skills with " + id + " is Not Found!"));
    skillsMatch.setSoft_skills(newSkills.getSoft_skills());
    skillsMatch.setHard_skills(newSkills.getHard_skills());
    return skillsService.save(skillsMatch);
  }

  @DeleteMapping(value = "/skills/{id}")
  public String deleteSkills(@PathVariable("id") @Min(1) Long id) {
    Skills skills = skillsService.findById(id)
        .orElseThrow(() -> new SkillsNotFoundException("Skills with " + id + " is Not Found!"));
    skillsService.deleteById(skills.getId());
    return "Skills with ID :" + id + " is deleted";
  }

  @Autowired
  private ProfileRepository profileRepository;

  @Autowired
  private SkillsRepository skillsRepository;

  @GetMapping("/profiles/{profileId}/skills")
  public ResponseEntity<List<Skills>> getAllSkillsByProfileId(
      @PathVariable(value = "profileId") Long profileId) {
    if (!profileRepository.existsById(profileId)) {
      throw new ProfileNotFoundException("Not found Profile with id = " + profileId);
    }
    List<Skills> skills = skillsRepository.findByProfileId(profileId);
    return new ResponseEntity<>(skills, HttpStatus.OK);
  }

  @PostMapping("/profiles/{profileId}/skills")
  public ResponseEntity<Skills> createSkills(@PathVariable(value = "profileId") Long profileId,
      @RequestBody Skills skillsRequest) {
    Skills skills = profileRepository.findById(profileId).map(profile -> {
      skillsRequest.setProfile(profile);
      return skillsRepository.save(skillsRequest);
    }).orElseThrow(() -> new ProfileNotFoundException("Not found Profile with id = " + profileId));
    return new ResponseEntity<>(skills, HttpStatus.CREATED);
  }

  @PutMapping("/profiles/{profileId}/skills")
  public Skills updateProfileSkills(@PathVariable("profileId") @Min(1) Long profileId,
      @Valid @RequestBody Skills newSkills) {
    if (!profileRepository.existsById(profileId)) {
      throw new ProfileNotFoundException("Not found Profile with id = " + profileId);
    }
    List<Skills> skills = skillsRepository.findByProfileId(profileId);
    Skills skillsMatch = skills.get(0);
    skillsMatch.setSoft_skills(newSkills.getSoft_skills());
    skillsMatch.setHard_skills(newSkills.getHard_skills());
    return skillsService.save(skillsMatch);
  }

  @DeleteMapping("/profiles/{profileId}/skills")
  public ResponseEntity<List<Skills>> deleteAllSkillsOfProfile(
      @PathVariable(value = "profileId") Long profileId) {
    if (!profileRepository.existsById(profileId)) {
      throw new ProfileNotFoundException("Not found Profile with id = " + profileId);
    }
    skillsRepository.deleteByProfileId(profileId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
