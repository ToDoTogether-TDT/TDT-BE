package TDT.backend.controller;

import TDT.backend.dto.post.EditPostReq;
import TDT.backend.dto.post.InsertPostReq;
import TDT.backend.dto.post.PostDetailResDto;
import TDT.backend.dto.post.PostPageResDto;
import TDT.backend.entity.Category;
import TDT.backend.service.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    @ApiOperation(value = "게시판 작성",notes = "게시판 작성")
    @PostMapping
    public ResponseEntity post(@RequestBody @Valid InsertPostReq insertPostReq) {

        Long postId = postService.post(insertPostReq);

        return new ResponseEntity<>(postId, HttpStatus.OK);
    }

    @ApiOperation(value = "게시물 조회",notes = "특정 게시물 조회")
    @GetMapping("/{category}/{postId}")
    public ResponseEntity getPost(@PathVariable Long postId) {

        PostDetailResDto response = postService.getPost(postId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "게시판 조회",notes = "여러개의 게시물 조회")
    @GetMapping("/{category}")
    public ResponseEntity getPostList(@RequestParam("page") int page,
                                      @PathVariable(required = false) Category category) {

        List<PostPageResDto> postList = postService.getPostList(page, category).getContent();

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @ApiOperation(value = "게시물 수정",notes = "하나의 게시물 수정")
    @PutMapping("/{category}/{postId}")
    public ResponseEntity editPost(@PathVariable Long postId,
                                   @PathVariable(required = false) Category category,
                                   @RequestBody @Valid EditPostReq editPostReqDto) {

        postService.editPost(postId, editPostReqDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @ApiOperation(value = "게시물 삭제",notes ="하나의 게시물 삭제")
    @DeleteMapping("/{category}/{postId}")
    public ResponseEntity deletePost(@PathVariable Category category,
                                     @PathVariable Long postId,
                                     @RequestParam String nickname) {

        postService.deletePost(postId, nickname);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
