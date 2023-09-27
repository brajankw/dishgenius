package brajankw.dishgenius.comment.response;

import java.time.LocalDateTime;

public record CommentResponse(

    String content,

    LocalDateTime time,

    Long dishId
) {

  public CommentResponse withDish(Long dishId) {

    return new CommentResponse(
        content(),
        time(),
        dishId
    );
  }
}
