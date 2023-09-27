package brajankw.dishgenius.comment.service.impl;

import brajankw.dishgenius.comment.entity.Comment;
import brajankw.dishgenius.comment.mapper.CommentMapper;
import brajankw.dishgenius.comment.repository.CommentRepository;
import brajankw.dishgenius.comment.request.CommentRequest;
import brajankw.dishgenius.comment.response.CommentResponse;
import brajankw.dishgenius.comment.service.CommentService;
import brajankw.dishgenius.error_handling.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final CommentMapper commentMapper;


  @Override
  public CommentResponse addComment(CommentRequest commentRequest) {
    Comment comment = commentMapper.mapRequestToEntity(commentRequest);

    return commentMapper.mapEntityToResponse(save(comment));
  }

  @Override
  public CommentResponse updateComment(Long id, CommentRequest commentRequest) {
    if (!commentRepository.existsById(id)) {

      throw new EntityNotFoundException(String.format("Comment with id: %d is not found.", id));
    }
    Comment comment = commentMapper.mapRequestToEntity(commentRequest);
    comment.setId(id);

    return commentMapper.mapEntityToResponse(save(comment));
  }

  @Override
  public CommentResponse getResponseById(Long id) {

    return commentMapper.mapEntityToResponse(getById(id));
  }

  @Override
  public Comment getById(Long id) {
    log.info("Getting comment with id: {}", id);

    return commentRepository.getCommentById(id);
  }

  @Override
  @Transactional
  public void delete(Comment comment) {
    log.info("Deleting comment with id: {}", comment.getId());
    commentRepository.delete(comment);
  }

  @Override
  public void deleteById(Long id) {
    delete(getById(id));
  }

  @Override
  @Transactional
  public Comment save(Comment comment) {
    log.info("Saving comment to database.");

    return commentRepository.save(comment);
  }
}
