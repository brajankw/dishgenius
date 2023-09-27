package brajankw.dishgenius.comment.repository;

import brajankw.dishgenius.comment.entity.Comment;
import brajankw.dishgenius.error_handling.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  default Comment getCommentById(Long id) {

    return findById(id).orElseThrow(()
        -> new EntityNotFoundException(String.format("Comment with id: %d is not found.", id)));
  }
}
