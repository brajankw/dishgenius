package brajankw.dishgenius.comment.entity;

import brajankw.dishgenius.dish.entity.Dish;
import brajankw.dishgenius.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
@Table(name = "comments")
public class Comment {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String content;

  @Column
  private LocalDateTime time = LocalDateTime.now();

  @ManyToOne
  @JoinColumn(name = "dish_id")
  @JsonBackReference(value = "comment_dish")
  private Dish dish;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonBackReference(value = "comment_user")
  private User user;
}
