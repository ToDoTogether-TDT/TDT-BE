package TDT.backend.dto;

import lombok.Data;

@Data
public class InsertCommentReq {
    private String writer;
    private String content;
}
