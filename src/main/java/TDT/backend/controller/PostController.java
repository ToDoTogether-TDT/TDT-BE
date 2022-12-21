package TDT.backend.controller;

import TDT.backend.dto.EditPostReq;
import TDT.backend.dto.InsertPostReq;
import TDT.backend.dto.PostResDto;
import TDT.backend.entity.Category;
import TDT.backend.entity.Post;
import TDT.backend.service.PostService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity post(@RequestBody InsertPostReq insertPostReq) {

        Long postId = postService.post(insertPostReq);

        return new ResponseEntity<>(postId, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity getPost(@PathVariable Long postId) {

        Post post = postService.getPost(postId);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/lounge")
    public ResponseEntity getPostList(@RequestParam("page") int page,
                                      @RequestParam(required = false) Category category) {

        List<PostResDto> postList = postService.getPostList(page, category).getContent();

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity editPost(@PathVariable Long postId,
                                   @RequestBody EditPostReq editPostReqDto) {

        postService.editPost(postId, editPostReqDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity deletePost(@PathVariable Long postId) {

        postService.deletePost(postId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
