package TDT.backend.controller;

import TDT.backend.dto.InsertCommentReq;
import TDT.backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/{postId}")
    public ResponseEntity writeComment(@PathVariable String postId,
                                       @RequestBody InsertCommentReq insertCommentReq) {

        return new ResponseEntity(HttpStatus.OK);
    }
}
