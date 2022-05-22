package com.ap.apbackend.Model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class Profile {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "profile_id")
  private Long id;
  private String name;
  private String lastname;
  private Date birth_date;
  private String nationality;
  private String email;
  private String about;
  private String ocupation;
  private String background_img_url;
  private String profile_img_url;
  private String address;

  @OneToMany(mappedBy = "profile")
  private Set<Education> educations = new HashSet<>();
  private Set<Experience> experiences = new HashSet<>();
}
