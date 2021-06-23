package life.kobefengfeng.community.community.mapper;

import life.kobefengfeng.community.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}