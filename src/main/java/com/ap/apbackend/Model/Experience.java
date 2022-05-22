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

@Setter
@Getter
@Entity
public class Experience {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "experience_id")
  private Long id;
  private String company;
  private String company_img_url;
  private String location;
  private String position;
  private Date start_date;
  private Date end_date;
  private String achievements;

  @ManyToOne
  @JoinColumn(name = "profile_id")
  private Profile profile;
}
