package brajankw.dishgenius.comment.service;

import brajankw.dishgenius.comment.entity.Comment;
import brajankw.dishgenius.comment.request.CommentRequest;
import brajankw.dishgenius.comment.response.CommentResponse;

public interface CommentService {

  CommentResponse addComment(CommentRequest commentRequest);

  CommentResponse updateComment(Long id, CommentRequest commentRequest);

  CommentResponse getResponseById(Long id);

  Comment getById(Long id);

  void delete(Comment comment);

  void deleteById(Long id);

  Comment save(Comment comment);
}
