package com.ap.apbackend.Model;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Skill {
  private String skill_name;
  private Number score;
}