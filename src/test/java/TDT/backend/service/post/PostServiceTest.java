package TDT.backend.service.post;

import TDT.backend.dto.post.EditPostReq;
import TDT.backend.dto.post.InsertPostReq;
import TDT.backend.dto.post.PostDetailResDto;
import TDT.backend.dto.post.PostPageResDto;
import TDT.backend.entity.Category;
import TDT.backend.entity.Member;
import TDT.backend.entity.Post;
import TDT.backend.entity.Role;
import TDT.backend.exception.BusinessException;
import TDT.backend.exception.ExceptionCode;
import TDT.backend.repository.member.MemberRepository;
import TDT.backend.repository.post.PostRepository;
import TDT.backend.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;

    Member member;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();

        member = new Member("JoMinJun", "jomj2214@gmail.com", Role.USER, "");
        memberRepository.save(member);
        member.profileUpdate("JoMinJun", "소개", Category.DAILY);
    }

    @Test
    @DisplayName("글 작성")
    public void createPostTest() {
        //given
        InsertPostReq insertPostReq = InsertPostReq.builder()
                .writer("JoMinJun")
                .category(Category.DAILY)
                .title("제목")
                .content("내용")
                .build();

        //when
        postService.post(insertPostReq);

        //then
        assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals(Category.DAILY, post.getCategory());
        assertEquals("제목", post.getTitle());
        assertEquals("내용", post.getContent());
        assertEquals("JoMinJun", post.getMember().getNickname());
    }
    
    @Test
    @DisplayName("게시글 상세조회")
    public void getPostTest() throws Exception {

        //given
        Post post = new Post(member, "제목", "내용", Category.PROMOTION, LocalDateTime.MAX);
        postRepository.save(post);

        //when
        PostDetailResDto response = postService.getPost(post.getId());

        //then
        assertNotNull(response);
        assertEquals("제목", post.getTitle());
        assertEquals("jomj2214@gmail.com", post.getMember().getEmail());
        assertEquals(LocalDateTime.MAX, response.getCreatedAt());
    }

    @Test
    @DisplayName("카테고리별 게시글 조회")
    public void getPostListByCategoryTest() throws Exception {
        //given
        List<Post> requestPosts1 = IntStream.range(1, 11)
                .mapToObj(i -> Post.builder()
                        .member(member)
                        .title("제목" + i)
                        .content("내용" + i)
                        .createdAt(LocalDateTime.MAX)
                        .category(Category.DAILY)
                        .build())
                .collect(Collectors.toList());

        List<Post> requestPosts2 = IntStream.range(11, 21)
                .mapToObj(i -> Post.builder()
                        .member(member)
                        .title("제목" + i)
                        .content("내용" + i)
                        .createdAt(LocalDateTime.MAX)
                        .category(Category.WORRY)
                        .build())
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts1);
        postRepository.saveAll(requestPosts2);

        //when
        Page<PostPageResDto> list = postService.getPostList(1, Category.DAILY);

        //then
        assertEquals(10, list.getContent().size());
        assertEquals("제목10", list.getContent().get(0).getTitle());
        assertEquals("제목1", list.getContent().get(9).getTitle());
    }

    @Test
    @DisplayName("게시글 수정")
    public void editPostTest() throws Exception {
        //given
        Post post = new Post(member, "제목", "내용", Category.PROMOTION, LocalDateTime.MAX);
        postRepository.save(post);

        EditPostReq editPostReq = EditPostReq.builder()
                .title("제목수정")
                .content("내용수정")
                .category(Category.DAILY)
                .nickname("JoMinJun")
                .build();

        //when
        postService.editPost(post.getId(), editPostReq);

        //then
        assertEquals("제목수정", post.getTitle());
        assertEquals("내용수정", post.getContent());
        assertEquals(Category.DAILY, post.getCategory());

    }

    @Test
    @DisplayName("게시글 삭제")
    public void deletePostTest() throws Exception {
        //given
        Post post = new Post(member, "제목", "내용", Category.PROMOTION, LocalDateTime.MAX);
        postRepository.save(post);

        //when
        postService.deletePost(post.getId(), "JoMinJun");

        //then
        assertEquals(0L, postRepository.count());
    }
    
    @Test
    @DisplayName("글 수정(삭제) 실패")
    public void postEditOrDeleteFailure() throws Exception {
        //given
        Post post = new Post(member, "제목", "내용", Category.PROMOTION, LocalDateTime.MAX);
        postRepository.save(post);

        Member member2 = new Member("GilDong", "gildong@gmail.com", Role.USER, "");
        memberRepository.save(member2);
        member2.profileUpdate("HongGilDong", "소개", Category.DAILY);

        EditPostReq editPostReq = EditPostReq.builder()
                .title("제목수정")
                .content("내용수정")
                .category(Category.DAILY)
                .nickname("HongGilDong")
                .build();

        //when

        //then
        assertThrows(BusinessException.class, () -> postService.deletePost(post.getId(), "HongGilDong"));
        assertThrows(BusinessException.class, () -> postService.editPost(post.getId(), editPostReq));
    }

    @Test
    @DisplayName("조회수 증가")
    public void addViewTest() throws Exception {
        //given
        Post post = new Post(member, "제목", "내용", Category.PROMOTION, LocalDateTime.MAX);
        postRepository.save(post);

        //when
        PostDetailResDto response = postService.getPost(post.getId());

        //then
        assertEquals(1, response.getView());

        PostDetailResDto response2 = postService.getPost(post.getId());

        assertEquals(2, response2.getView());
    }

}