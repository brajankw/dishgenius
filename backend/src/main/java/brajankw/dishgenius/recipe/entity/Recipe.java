package brajankw.dishgenius.recipe.entity;

import brajankw.dishgenius.dish.entity.Dish;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipes")
public class Recipe {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private int prepareTimeMinutes;

  @Column
  private String description;

  @Column
  @Enumerated(EnumType.STRING)
  private RecipeDifficult difficulty;

  @OneToOne(cascade = CascadeType.ALL)
  @JsonBackReference(value = "recipe_dish")
  @JoinColumn(name = "dish_id", referencedColumnName = "id")
  private Dish dish;
}
