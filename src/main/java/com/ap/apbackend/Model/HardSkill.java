package com.ap.apbackend.Model;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class HardSkill {
  private String soft_skill_name;
  private Number score;
}