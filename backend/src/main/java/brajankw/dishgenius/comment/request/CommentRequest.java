package brajankw.dishgenius.comment.request;

public record CommentRequest(

    String content,

    Long dishId
) {}
