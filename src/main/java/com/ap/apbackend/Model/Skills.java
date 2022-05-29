package com.ap.apbackend.Model;

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
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "soft_skills", joinColumns = @JoinColumn(name = "soft_skills_id"))
  @OrderColumn
  private List<Skill> soft_skills;
  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "hard_skills", joinColumns = @JoinColumn(name = "hard_skills_id"))
  @OrderColumn
  private List<Skill> hard_skills;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "profile_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Profile profile;

}
