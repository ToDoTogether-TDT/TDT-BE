package TDT.backend.controller;

import TDT.backend.dto.comment.EditCommentReq;
import TDT.backend.dto.comment.InsertCommentReq;
import TDT.backend.service.CommentService;
import TDT.backend.common.auth.MemberDetails;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;
    @ApiOperation(value = "댓글 작성")
    @PostMapping
    public ResponseEntity createComment(@RequestParam(value = "postId", required = false) Long postId,
                                        @RequestParam(value = "studyId", required = false) Long studyId,
                                        @RequestBody InsertCommentReq insertCommentReq,
                                        @AuthenticationPrincipal MemberDetails memberDetails) {

        commentService.createComment(postId, studyId, insertCommentReq, memberDetails.getMember());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 수정")
    @PutMapping("/{commentId}")
    public ResponseEntity editComment(@PathVariable Long commentId,
                                      @RequestBody EditCommentReq editCommentReq,
                                      @AuthenticationPrincipal MemberDetails memberDetails) {

        commentService.editComment(commentId, editCommentReq, memberDetails.getMember());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 삭제")
    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long commentId,
                                        @AuthenticationPrincipal MemberDetails memberDetails) {
        commentService.deleteComment(commentId, memberDetails.getMember());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
