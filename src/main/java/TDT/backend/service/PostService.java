package TDT.backend.service;

import TDT.backend.dto.comment.CommentRes;
import TDT.backend.dto.post.EditPostReq;
import TDT.backend.dto.post.InsertPostReq;
import TDT.backend.dto.post.PostDetailResDto;
import TDT.backend.dto.post.PostPageResDto;
import TDT.backend.entity.Category;
import TDT.backend.entity.Member;
import TDT.backend.entity.Post;
import TDT.backend.exception.BusinessException;
import TDT.backend.exception.ExceptionCode;
import TDT.backend.repository.comment.CommentRepository;
import TDT.backend.repository.member.MemberRepository;
import TDT.backend.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    public Long post(InsertPostReq insertPostReq) {
        Member member = memberRepository.findByNickname(insertPostReq.getWriter())
                .orElseThrow(() -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS));

        Post post = Post.builder()
                .title(insertPostReq.getTitle())
                .content(insertPostReq.getContent())
                .member(member)
                .category(insertPostReq.getCategory())
                .createdAt(LocalDateTime.now())
                .build();

        postRepository.save(post);

        return post.getId();
    }

    @Transactional
    public PostDetailResDto getPost(Long postId) {

        Post post = findPost(postId);

        List<CommentRes> comments = post.getComments().stream().map(comment ->
                         CommentRes.builder()
                        .writer(comment.getMember().getNickname())
                        .content(comment.getContent())
                        .createdAt(comment.getCreatedAt())
                        .build()
        ).collect(Collectors.toList());
        post.addView();

        PostDetailResDto response = PostDetailResDto.builder()
                .postId(post.getId())
                .writer(post.getMember().getNickname())
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory())
                .comments(comments)
                .createdAt(post.getCreatedAt())
                .view(post.getView())
                .build();

        return response;
    }

    /**
     * ##추가->> 카테고리 특정없이 모든 게시판 조회
     */
    public Page<PostPageResDto> getAllKindOfPost(Pageable pageable) {
        return postRepository.findAllByPageable(pageable);
    }


    @Transactional(readOnly = true)
    public Page<PostPageResDto> getPostList(int page, String category) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("id").descending());

        return postRepository.getList(pageable, category);
    }

    public void editPost(Long postId, EditPostReq editPostReqDto) {

        Post post = findPost(postId);

        if (post.getMember().getNickname().equals(editPostReqDto.getNickname())) {
            post.edit(editPostReqDto.getTitle(), editPostReqDto.getContent(), editPostReqDto.getCategory());
        } else throw new BusinessException(ExceptionCode.UNAUTHORIZED_ERROR);
    }

    public void deletePost(Long postId, String nickname) {

        Post post = findPost(postId);

        if (post.getMember().getNickname().equals(nickname)) {
            postRepository.delete(post);
        } else throw new BusinessException(ExceptionCode.UNAUTHORIZED_ERROR);
    }

    private Post findPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.POST_NOT_EXISTS));

        return post;
    }

}
