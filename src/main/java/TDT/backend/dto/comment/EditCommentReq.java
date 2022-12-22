package TDT.backend.dto.comment;

import lombok.Data;

@Data
public class EditCommentReq {
    private String writer;
    private String content;
}
