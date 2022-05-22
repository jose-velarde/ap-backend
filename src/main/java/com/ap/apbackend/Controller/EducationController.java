package com.ap.apbackend.Controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.ap.apbackend.Exceptions.EducationNotFoundException;
import com.ap.apbackend.Model.Education;
import com.ap.apbackend.Service.EducationService;

import org.springframework.beans.factory.annotation.Autowired;
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
}
