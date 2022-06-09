package com.ap.apbackend.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "skills")
public class Skills {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "skills_id")
  private Long id;
  // @ElementCollection
  // @CollectionTable(name = "soft_skills", joinColumns = @JoinColumn(name =
  // "soft_skills_id"))
  // private List<Skill> soft_skills;
  // @ElementCollection
  // @CollectionTable(name = "hard_skills", joinColumns = @JoinColumn(name =
  // "hard_skills_id"))
  // private List<Skill> hard_skills;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "profile_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @Cascade(value = { CascadeType.ALL })
  @JsonIgnore
  private Profile profile;

}
