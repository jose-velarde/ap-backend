package com.ap.apbackend.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
  private String grade;
  private Date start_date;
  private Date end_date;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "profile_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Profile profile;

}
