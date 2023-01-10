package TDT.backend.entity;

import lombok.Getter;

@Getter
public enum NoticeCategory {

    studyJoin("스터디 요청","스터디"),
    postComment("게시판 댓글", "댓글"),
    studyComment("스터디 댓글", "댓글");

    private String name;
    private String type;

    NoticeCategory(String name, String type) {
        this.name = name;
        this.type = type;
    }

}

