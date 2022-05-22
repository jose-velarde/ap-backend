package com.ap.apbackend.Controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.ap.apbackend.Exceptions.ProfileNotFoundException;
import com.ap.apbackend.Model.Experience;
import com.ap.apbackend.Model.Profile;
import com.ap.apbackend.Repository.ExperienceRepository;
import com.ap.apbackend.Repository.ProfileRepository;
import com.ap.apbackend.Service.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProfileController {
  ProfileService profileService;

  @Autowired
  public ProfileController(ProfileService profileService) {
    this.profileService = profileService;
  }

  @GetMapping(value = "/profiles")
  public List<Profile> getAllProfiles() {
    return profileService.getAllProfiles();
  }

  @GetMapping(value = "/profiles/{id}")
  public Profile getProfileById(@PathVariable("id") @Min(1) Long id) {
    Profile profile = profileService.findById(id)
        .orElseThrow(() -> new ProfileNotFoundException("Profile with " + id + " is Not Found!"));
    return profile;
  }

  @PostMapping(value = "/profiles")
  public Profile addProfile(@Valid @RequestBody Profile profile) {
    return profileService.save(profile);
  }

  @PutMapping(value = "/profiles/{id}")
  public Profile updateProfile(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody Profile newProfile) {
    Profile profileMatch = profileService.findById(id)
        .orElseThrow(() -> new ProfileNotFoundException("Profile with " + id + " is Not Found!"));
    profileMatch.setName(newProfile.getName());
    profileMatch.setLastname(newProfile.getLastname());
    profileMatch.setEmail(newProfile.getEmail());
    return profileService.save(profileMatch);
  }

  @DeleteMapping(value = "/profiles/{id}")
  public String deleteProfile(@PathVariable("id") @Min(1) Long id) {
    Profile profile = profileService.findById(id)
        .orElseThrow(() -> new ProfileNotFoundException("Profile with " + id + " is Not Found!"));
    profileService.deleteById(profile.getId());
    return "Profile with ID :" + id + " is deleted";
  }

  @Autowired
  private ProfileRepository profileRepository;

  @Autowired
  private ExperienceRepository experienceRepository;

  @GetMapping("/profiles/{profileId}/experiences")
  public ResponseEntity<List<Experience>> getAllExperiencesByProfileId(
      @PathVariable(value = "profileId") Long profileId) {
    if (!profileRepository.existsById(profileId)) {
      throw new ProfileNotFoundException("Not found Profile with id = " + profileId);
    }
    List<Experience> experiences = experienceRepository.findByProfileId(profileId);
    return new ResponseEntity<>(experiences, HttpStatus.OK);
  }

  @PostMapping("/profiles/{profileId}/experiences")
  public ResponseEntity<Experience> createExperience(@PathVariable(value = "profileId") Long profileId,
      @RequestBody Experience experienceRequest) {
    Experience experience = profileRepository.findById(profileId).map(profile -> {
      experienceRequest.setProfile(profile);
      return experienceRepository.save(experienceRequest);
    }).orElseThrow(() -> new ProfileNotFoundException("Not found Profile with id = " + profileId));
    return new ResponseEntity<>(experience, HttpStatus.CREATED);
  }

  @DeleteMapping("/profiles/{profileId}/experiences")
  public ResponseEntity<List<Experience>> deleteAllExperiencesOfProfile(
      @PathVariable(value = "profileId") Long profileId) {
    if (!profileRepository.existsById(profileId)) {
      throw new ProfileNotFoundException("Not found Profile with id = " + profileId);
    }
    experienceRepository.deleteByProfileId(profileId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
