package TDT.backend.service;

import TDT.backend.dto.comment.EditCommentReq;
import TDT.backend.dto.comment.InsertCommentReq;
import TDT.backend.entity.Comment;
import TDT.backend.entity.Member;
import TDT.backend.entity.Post;
import TDT.backend.exception.BusinessException;
import TDT.backend.exception.ExceptionCode;
import TDT.backend.repository.comment.CommentRepository;
import TDT.backend.repository.member.MemberRepository;
import TDT.backend.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    public void createComment(Long postId, InsertCommentReq insertCommentReq) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.POST_NOT_EXISTS));

        Member member = memberRepository.findByNickname(insertCommentReq.getWriter())
                .orElseThrow(() -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS));

        Comment comment = Comment.builder()
                .post(post)
                .member(member)
                .content(insertCommentReq.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        commentRepository.save(comment);

    }

    public void editComment(Long commentId, EditCommentReq editCommentReq) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.COMMENT_NOT_EXISTS));

        if (comment.getMember().getNickname().equals(editCommentReq.getWriter())) {
            comment.edit(editCommentReq.getContent());
        } else throw new BusinessException(ExceptionCode.UNAUTHORIZED_ERROR);
    }

    public void deleteComment(Long commentId, String nickname) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.COMMENT_NOT_EXISTS));

        if (comment.getMember().getNickname().equals(nickname)) {
            commentRepository.delete(comment);
        } else throw new BusinessException(ExceptionCode.UNAUTHORIZED_ERROR);

    }
}
