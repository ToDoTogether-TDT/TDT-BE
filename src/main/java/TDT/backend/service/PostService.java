package TDT.backend.service;

import TDT.backend.dto.comment.CommentRes;
import TDT.backend.dto.post.EditPostReq;
import TDT.backend.dto.post.InsertPostReq;
import TDT.backend.dto.post.PostDetailResDto;
import TDT.backend.dto.post.PostPageResDto;
import TDT.backend.entity.Member;
import TDT.backend.entity.Post;
import TDT.backend.exception.BusinessException;
import TDT.backend.exception.ExceptionCode;
import TDT.backend.repository.comment.CommentRepository;
import TDT.backend.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public Long post(InsertPostReq insertPostReq, Member member) {

        Post post = insertPostReq.toEntity(member);

        postRepository.save(post);

        return post.getId();
    }

    @Transactional
    public PostDetailResDto getPost(Long postId) {

        Post post = findPost(postId);

        List<CommentRes> comments = commentRepository.findCommentsByPostIdOrStudyId(postId, null);
        post.addView();

        PostDetailResDto response = PostDetailResDto.of(post, comments);

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

    public void editPost(Long postId, EditPostReq editPostReqDto, Member member) {

        Post post = findPost(postId);

        if (post.getMember().getId().equals(member.getId())) {
            post.edit(editPostReqDto.getTitle(), editPostReqDto.getContent(), editPostReqDto.getCategory());
        } else throw new BusinessException(ExceptionCode.UNAUTHORIZED_ERROR);
    }

    public void deletePost(Long postId, Member member) {

        Post post = findPost(postId);

        if (post.getMember().getId().equals(member.getId())) {
            postRepository.delete(post);
        } else throw new BusinessException(ExceptionCode.UNAUTHORIZED_ERROR);
    }

    private Post findPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.POST_NOT_EXISTS));

        return post;
    }

}
