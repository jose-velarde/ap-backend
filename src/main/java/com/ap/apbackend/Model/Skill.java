package com.ap.apbackend.Model;

import javax.persistence.Embeddable;

@Embeddable
public class Skill {
  private String skill_name;
  private Number score;
}