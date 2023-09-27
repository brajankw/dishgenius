package brajankw.dishgenius.ingredient_in_dish.entity;

import brajankw.dishgenius.dish.entity.Dish;
import brajankw.dishgenius.ingredient.entity.Ingredient;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "ingredient_in_dish")
public class IngredientInDish {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private Double weight;

  @OneToOne(cascade = CascadeType.ALL)
  private Ingredient ingredient;

  @ManyToOne
  @JoinColumn(name = "dish_id")
  @JsonBackReference(value = "ingredientInDish_dish")
  private Dish dish;
}
