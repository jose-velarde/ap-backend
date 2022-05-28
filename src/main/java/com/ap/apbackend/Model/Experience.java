package com.ap.apbackend.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
  @Column
  @ElementCollection(targetClass = String.class)
  private List<String> achievements;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "profile_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Profile profile;
}
