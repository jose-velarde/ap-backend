package com.ap.apbackend.Model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "skill_name", column = @Column(name = "soft_skills_name")),
      @AttributeOverride(name = "score", column = @Column(name = "soft_skills_score")),
  })
  private List<Skill> soft_skills;
  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "skill_name", column = @Column(name = "hard_skills_name")),
      @AttributeOverride(name = "score", column = @Column(name = "hard_skills_score")),
  })
  private List<Skill> hard_skills;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "profile_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Profile profile;

}
