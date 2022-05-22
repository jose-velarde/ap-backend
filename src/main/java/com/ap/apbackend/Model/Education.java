package com.ap.apbackend.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Education {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "education_id")
  private Long id;
  private String degree;
  private String institution;
  private String location;
  private String institution_img_url;
  private String career;
  private Long grade;
  private Date start_date;
  private Date end_date;

  @ManyToOne
  @JoinColumn(name = "profile_id")
  private Profile profile;
}
