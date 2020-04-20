package com.volkangurbuz.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingredient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;
  private BigDecimal amount;

  @ManyToOne private Recipe recipe;
  // we do want that loaded everytime that is the default behavior but sometimes doine something
  // like this, it is handy to show the intent
  @OneToOne(fetch = FetchType.EAGER)
  private UnitOfMeasure unitOfMeasure;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public UnitOfMeasure getUnitOfMeasure() {
    return unitOfMeasure;
  }
}
