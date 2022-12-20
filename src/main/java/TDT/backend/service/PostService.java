package TDT.backend.service;

import TDT.backend.dto.InsertPostReq;
import TDT.backend.entity.Member;
import TDT.backend.entity.Post;
import TDT.backend.exception.BusinessException;
import TDT.backend.exception.ExceptionCode;
import TDT.backend.repository.member.MemberRepository;
import TDT.backend.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public void post(InsertPostReq insertPostReq) {
        Member member = memberRepository.findByNickname(insertPostReq.getWriter())
                .orElseThrow(() -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS));

        Post post = Post.builder()
                .title(insertPostReq.getTitle())
                .content(insertPostReq.getContent())
                .member(member)
                .category(insertPostReq.getCategory())
                .createdAt(insertPostReq.getCreateAt())
                .build();

        postRepository.save(post);

    }
}
