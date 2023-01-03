package TDT.backend.controller;

import TDT.backend.dto.comment.EditCommentReq;
import TDT.backend.dto.comment.InsertCommentReq;
import TDT.backend.service.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;
    @ApiOperation(value = "댓글 작성")
    @PostMapping("/comment")
    public ResponseEntity createComment(@RequestParam(value = "postId", required = false) Long postId,
                                        @RequestParam(value = "studyId", required = false) Long studyId,
                                        @RequestBody InsertCommentReq insertCommentReq) {

        log.info(String.valueOf(postId));
        log.info(String.valueOf(studyId));

        commentService.createComment(postId, insertCommentReq);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     TodoList
     스터디 댓글
     * @param commentId
     * @param editCommentReq
     * @return
     */

    @ApiOperation(value = "댓글 수정")
    @PutMapping("/comment/{commentId}")
    public ResponseEntity editComment(@RequestParam(value = "postId", required = false) Long postId,
                                      @RequestParam(value = "studyId", required = false) Long studyId,
                                      @PathVariable Long commentId,
                                      @RequestBody EditCommentReq editCommentReq) {
        commentService.editComment(commentId, editCommentReq);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 삭제")
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity deleteComment(@RequestParam(value = "postId", required = false) Long postId,
                                        @RequestParam(value = "studyId", required = false) Long studyId,
                                        @PathVariable Long commentId,
                                        @RequestParam String nickname) {
        commentService.deleteComment(commentId, nickname);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
