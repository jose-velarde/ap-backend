package com.ap.apbackend.Service;

import java.util.List;
import java.util.Optional;

import com.ap.apbackend.Model.Profile;
import com.ap.apbackend.Repository.ProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService implements IProfileService {

  ProfileRepository profileRepository;

  @Autowired
  public ProfileService(ProfileRepository profileRepository) {
    this.profileRepository = profileRepository;
  }

  @Override
  public List<Profile> getAllProfiles() {
    return profileRepository.findAll();
  };

  @Override
  public Optional<Profile> findById(Long id) {
    return profileRepository.findById(id);
  }

  @Override
  public Profile save(Profile profile) {
    return profileRepository.save(profile);
  }

  @Override
  public void deleteById(Long id) {
    profileRepository.deleteById(id);
  }

}
