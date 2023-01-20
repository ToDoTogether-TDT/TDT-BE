//package TDT.backend.controller;
//
//import TDT.backend.dto.post.EditPostReq;
//import TDT.backend.dto.post.InsertPostReq;
//import TDT.backend.entity.Category;
//import TDT.backend.entity.Member;
//import TDT.backend.entity.Post;
//import TDT.backend.entity.Role;
//import TDT.backend.repository.member.MemberRepository;
//import TDT.backend.repository.post.PostRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//import static org.hamcrest.Matchers.is;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//@SpringBootTest
//class PostControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper mapper;
//    @Autowired
//    private PostRepository postRepository;
//    @Autowired
//    private MemberRepository memberRepository;
//
//    Member member;
//
//    @BeforeEach
//    void clean() {
//        postRepository.deleteAll();
//
//        member = new Member("name", "email@gmai.com", Role.USER, "picture");
//        member.profileUpdate("writer", "introduction", Category.DAILY);
//        memberRepository.save(member);
//
//    }
//
//    @Test
//    @DisplayName("글 작성시 title값 필수")
//    public void checkTitleTest() throws Exception {
//        //given
//        InsertPostReq insertPostReq = InsertPostReq.builder()
//                .title("").content("content").writer("writer").category(Category.DAILY)
//                .build();
//
//        String content = mapper.writeValueAsString(insertPostReq);
//
//        //expected
//        mockMvc.perform(post("/post")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
//                )
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.fieldErrors.[0].reason").value("제목을 입력해주세요"))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("글 작성 요청 시 DB에 값 저장")
//    public void writePostTest() throws Exception {
//        //given
//        InsertPostReq insertPostReq = InsertPostReq.builder()
//                .title("title").content("content").writer("writer").category(Category.DAILY)
//                .build();
//
//        String content = mapper.writeValueAsString(insertPostReq);
//
//        //when
//        mockMvc.perform(post("/post")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
//                )
//                .andExpect(status().isOk())
//                .andDo(print());
//
//        //then
//        assertEquals(1L, postRepository.count());
//
//        Post post = postRepository.findAll().get(0);
//        assertEquals("title", post.getTitle());
//        assertEquals("content", post.getContent());
//    }
//
//    @Test
//    @DisplayName("글 상세조회")
//    public void getPostTest() throws Exception {
//        //given
//        Post post = new Post(member, "제목", "내용", Category.PROMOTION, LocalDateTime.MAX);
//        postRepository.save(post);
//
//        //expected
//        mockMvc.perform(get("/post/{postId}", post.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.postId").value(post.getId()))
//                .andExpect(jsonPath("$.title").value(post.getTitle()))
//                .andExpect(jsonPath("$.category").value(post.getCategory().toString()))
//                .andExpect(jsonPath("$.view").value(post.getView() + 1))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("카테고리 별 2페이지")
//    public void getPostsByCategoryTest() throws Exception {
//        //given
//        List<Post> requestPosts1 = IntStream.range(1, 21)
//                .mapToObj(i -> Post.builder()
//                        .member(member)
//                        .title("제목" + i)
//                        .content("내용" + i)
//                        .createdAt(LocalDateTime.MAX)
//                        .category(Category.DAILY)
//                        .build())
//                .collect(Collectors.toList());
//
//        List<Post> requestPosts2 = IntStream.range(21, 41)
//                .mapToObj(i -> Post.builder()
//                        .member(member)
//                        .title("제목" + i)
//                        .content("내용" + i)
//                        .createdAt(LocalDateTime.MAX)
//                        .category(Category.WORRY)
//                        .build())
//                .collect(Collectors.toList());
//
//        postRepository.saveAll(requestPosts1);
//        postRepository.saveAll(requestPosts2);
//
//        //expected
//        mockMvc.perform(get("/lounge")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("page", "2")
//                        .param("category", "DAILY")
//                )
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()", is(10)))
//                .andExpect(jsonPath("$.[0].id").value(10))
//                .andExpect(jsonPath("$.[0].category").value(Category.DAILY.toString()))
//                .andExpect(jsonPath("$.[0].title").value("제목10"))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("전체 게시글 2페이지 조회(카테고리 param 입력x)")
//    public void getAllPostTest() throws Exception {
//        //given
//        List<Post> requestPosts1 = IntStream.range(1, 11)
//                .mapToObj(i -> Post.builder()
//                        .member(member)
//                        .title("제목" + i)
//                        .content("내용" + i)
//                        .createdAt(LocalDateTime.MAX)
//                        .category(Category.DAILY)
//                        .build())
//                .collect(Collectors.toList());
//
//        List<Post> requestPosts2 = IntStream.range(11, 21)
//                .mapToObj(i -> Post.builder()
//                        .member(member)
//                        .title("제목" + i)
//                        .content("내용" + i)
//                        .createdAt(LocalDateTime.MAX)
//                        .category(Category.WORRY)
//                        .build())
//                .collect(Collectors.toList());
//
//        postRepository.saveAll(requestPosts1);
//        postRepository.saveAll(requestPosts2);
//
//        //expected
//        mockMvc.perform(get("/lounge")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("page", "2")
//                )
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()", is(10)))
//                .andExpect(jsonPath("$.[0].id").value(10))
//                .andExpect(jsonPath("$.[0].category").value(Category.DAILY.toString()))
//                .andExpect(jsonPath("$.[0].title").value("제목10"))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("글 내용 수정")
//    public void editPostTest() throws Exception {
//        //given
//        Post post = new Post(member, "제목", "내용", Category.PROMOTION, LocalDateTime.MAX);
//        postRepository.save(post);
//
//        EditPostReq editPostReq = EditPostReq.builder()
//                .nickname(member.getNickname())
//                .title("title")
//                .content("수정")
//                .category(Category.DAILY)
//                .build();
//
//        String content = mapper.writeValueAsString(editPostReq);
//
//        //expected
//        mockMvc.perform(put("/post/{postId}", post.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
//                )
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("게시글 삭제")
//    public void deletePostTest() throws Exception {
//        //given
//        Post post = new Post(member, "제목", "내용", Category.PROMOTION, LocalDateTime.MAX);
//        postRepository.save(post);
//
//        String nickname = post.getMember().getNickname();
//
//        //expected
//        mockMvc.perform(delete("/post/{postId}", post.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("nickname", nickname)
//                )
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
