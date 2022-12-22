package TDT.backend.service;

import TDT.backend.dto.InsertCommentReq;
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
}
