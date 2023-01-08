package TDT.backend.service;

import TDT.backend.common.utils.CategoryClassifier;
import TDT.backend.dto.comment.EditCommentReq;
import TDT.backend.dto.comment.InsertCommentReq;
import TDT.backend.entity.*;
import TDT.backend.exception.BusinessException;
import TDT.backend.exception.ExceptionCode;
import TDT.backend.repository.comment.CommentRepository;
import TDT.backend.repository.notice.NoticeRepository;
import TDT.backend.repository.post.PostRepository;
import TDT.backend.repository.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final TeamRepository teamRepository;

    private final NoticeRepository noticeRepository;
    public void createComment(Long postId, Long studyId, InsertCommentReq insertCommentReq, Member member) {

        if (!Objects.isNull(postId)) {
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new BusinessException(ExceptionCode.POST_NOT_EXISTS));

            Comment comment = insertCommentReq.toEntity(post, member);
            NoticeCategory nCategory = CategoryClassifier.classifier(String.valueOf(post.getCategory()));
            System.out.println(nCategory);
            noticeRepository.save(Notice.of(member, nCategory));
            commentRepository.save(comment);
        } else {
            Team team = teamRepository.findById(studyId)
                    .orElseThrow(() -> new BusinessException(ExceptionCode.TEAM_NOT_EXISTS));
            Comment comment = insertCommentReq.toEntity(team, member);
            NoticeCategory nCategory = CategoryClassifier.classifier(String.valueOf(team.getCategory()));

            noticeRepository.save(Notice.of(member, nCategory));
            commentRepository.save(comment);
        }
    }

    public void editComment(Long commentId, EditCommentReq editCommentReq, Member member) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.COMMENT_NOT_EXISTS));

        if (comment.getMember().getId().equals(member.getId())) {
            comment.edit(editCommentReq.getContent());
        } else throw new BusinessException(ExceptionCode.UNAUTHORIZED_ERROR);
    }

    public void deleteComment(Long commentId, Member member) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.COMMENT_NOT_EXISTS));

        if (comment.getMember().getId().equals(member.getId())) {
            commentRepository.delete(comment);
        } else throw new BusinessException(ExceptionCode.UNAUTHORIZED_ERROR);

    }
}
