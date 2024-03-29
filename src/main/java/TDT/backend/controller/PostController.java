package TDT.backend.controller;

import TDT.backend.dto.post.EditPostReq;
import TDT.backend.dto.post.InsertPostReq;
import TDT.backend.dto.post.PostDetailResDto;
import TDT.backend.dto.post.PostPageResDto;
import TDT.backend.entity.Category;
import TDT.backend.service.PostService;
import TDT.backend.common.auth.MemberDetails;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @ApiOperation(value = "게시판 작성", notes = "게시판 작성")
    @PostMapping
    public ResponseEntity post(@RequestBody @Valid InsertPostReq insertPostReq,
                               @AuthenticationPrincipal MemberDetails memberDetails) {

        Long postId = postService.post(insertPostReq, memberDetails.getMember());

        return new ResponseEntity<>(postId, HttpStatus.OK);
    }

    @ApiOperation(value = "게시물 조회", notes = "특정 게시물 조회")
    @GetMapping("/{category}/{postId}")
    public ResponseEntity getPost(@PathVariable("category") String category,
                                  @PathVariable Long postId) {

        PostDetailResDto response = postService.getPost(postId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "모든 게시판 조회", notes = "##추가 -> 모든 게시판 조회")
    @GetMapping
    public ResponseEntity getAllKindOfPost(@RequestParam(value = "page", defaultValue = "1") int page) {
        return new ResponseEntity<>(postService.getAllKindOfPost(page), HttpStatus.OK);
    }

    @ApiOperation(value = "특정 카테고리게시판 조회", notes = "##추가 -> 특정 카테고리 게시물 조회")
    @GetMapping("/{category}")
    public ResponseEntity getPostList(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @PathVariable String category) {

        List<PostPageResDto> postList = postService.getPostList(page, category).getContent();

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @ApiOperation(value = "게시물 수정", notes = "하나의 게시물 수정")
    @PutMapping("/{category}/{postId}")
    public ResponseEntity editPost(@PathVariable(value = "postId") Long postId,
                                   @PathVariable(value = "category") Category category,
                                   @RequestBody @Valid EditPostReq editPostReqDto,
                                   @AuthenticationPrincipal MemberDetails memberDetails) {

        postService.editPost(postId, editPostReqDto, memberDetails.getMember());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "게시물 삭제", notes = "하나의 게시물 삭제")
    @DeleteMapping("/{category}/{postId}")
    public ResponseEntity deletePost(@PathVariable(value = "category") Category category,
                                     @PathVariable(value = "postId") Long postId,
                                     @AuthenticationPrincipal MemberDetails memberDetails) {

        postService.deletePost(postId, memberDetails.getMember());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
