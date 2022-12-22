package TDT.backend.repository.comment;

import TDT.backend.dto.comment.CommentRes;

import java.util.List;

public interface CommentRepositoryCustom {
    List<CommentRes> findCommentsByPostId(Long postId);
}
