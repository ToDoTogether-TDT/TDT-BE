package TDT.backend.dto.comment;

import lombok.Data;

@Data
public class InsertCommentReq {
    private String writer;
    private String content;
}
