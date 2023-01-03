package TDT.backend.controller;

import TDT.backend.dto.comment.EditCommentReq;
import TDT.backend.dto.comment.InsertCommentReq;
import TDT.backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/{postId}")
    public ResponseEntity createComment(@PathVariable Long postId,
                                        @RequestBody InsertCommentReq insertCommentReq) {

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
    @PutMapping("/comment/{commentId}")
    public ResponseEntity editComment(@PathVariable Long commentId,
                                      @RequestBody EditCommentReq editCommentReq) {
        commentService.editComment(commentId, editCommentReq);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long commentId,
                                        @RequestParam String nickname) {
        commentService.deleteComment(commentId, nickname);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
