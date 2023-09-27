package brajankw.dishgenius.comment.mapper;

import brajankw.dishgenius.comment.entity.Comment;
import brajankw.dishgenius.comment.request.CommentRequest;
import brajankw.dishgenius.comment.response.CommentResponse;
import brajankw.dishgenius.dish.repository.DishRepository;
import brajankw.dishgenius.error_handling.exception.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentMapper {

  private final DishRepository dishRepository;
  private final ObjectMapper mapper;

  public Comment mapRequestToEntity(CommentRequest commentRequest) {
    log.info("Mapping comment request to comment entity.");
    try {
      Comment comment = mapper.readValue(mapper.writeValueAsString(commentRequest), Comment.class);
      comment.setDish(dishRepository.getDishById(commentRequest.dishId()));

      return comment;
    } catch (JsonProcessingException e) {

      throw new BadRequestException(
          "Error while mapping comment request to comment entity " + e.getMessage());
    }
  }

  public CommentResponse mapEntityToResponse(Comment comment) {
    log.info("Mapping comment entity to comment response.");
    try {

      return mapper.readValue(mapper.writeValueAsString(comment), CommentResponse.class).withDish(comment.getDish().getId());
    } catch (JsonProcessingException e) {

      throw new BadRequestException(
          "Error while mapping comment request to comment response " + e.getMessage());
    }
  }
}
