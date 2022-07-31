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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
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
}
