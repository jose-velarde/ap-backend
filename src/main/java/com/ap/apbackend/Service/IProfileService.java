package com.ap.apbackend.Service;

import java.util.List;
import java.util.Optional;

import com.ap.apbackend.Model.Profile;

public interface IProfileService {
  List<Profile> getAllProfiles();

  Optional<Profile> findById(Long id);

  Profile save(Profile profile);

  void deleteById(Long id);

}
