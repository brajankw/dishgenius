package brajankw.dishgenius.comment.controller;

import brajankw.dishgenius.comment.request.CommentRequest;
import brajankw.dishgenius.comment.response.CommentResponse;
import brajankw.dishgenius.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

  private final CommentService commentService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CommentResponse addComment(@RequestBody CommentRequest commentRequest) {
    log.info("POST-request: creating new comment");

    return commentService.addComment(commentRequest);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CommentResponse updateComment(@PathVariable Long id,
                                       @RequestBody CommentRequest commentRequest) {
    log.info("PUT-request: updating comment with id: {}", id);

    return commentService.updateComment(id, commentRequest);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteComment(@PathVariable Long id) {
    log.info("DELETE-request: deleting comment with id: {}", id);
    commentService.deleteById(id);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CommentResponse getCommentById(@PathVariable Long id) {
    log.info("GET-request: getting comment with id: {}", id);

    return commentService.getResponseById(id);
  }
}
