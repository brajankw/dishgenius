package brajankw.dishgenius.dish.entity;

import brajankw.dishgenius.comment.entity.Comment;
import brajankw.dishgenius.dish.entity.enums.DishType;
import brajankw.dishgenius.image.entity.Image;
import brajankw.dishgenius.ingredient_in_dish.entity.IngredientInDish;
import brajankw.dishgenius.recipe.entity.Recipe;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "dishes")
public class Dish {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private String description;

  @Column
  private Double rating = 0.0;

  @Column
  @Enumerated(EnumType.STRING)
  private DishType dishType;

  @OneToOne(mappedBy = "dish")
  @JsonManagedReference(value = "recipe_dish")
  private Recipe recipe;

  @OneToMany(mappedBy = "dish")
  @JsonManagedReference(value = "comment_dish")
  private List<Comment> comments = new ArrayList<>();

  @OneToMany(mappedBy = "dish")
  @JsonManagedReference(value = "image_dish")
  private List<Image> images = new ArrayList<>();

  @OneToMany(mappedBy = "dish")
  @JsonManagedReference(value = "ingredientInDish_dish")
  private List<IngredientInDish> ingredientsInDish = new ArrayList<>();
}
