package TDT.backend.entity;

import lombok.Getter;

@Getter
public enum NoticeCategory {

    study("스터디 알림","스터디"),
    post("게시판 알림", "게시판"),
    comment("댓글 알림", "댓글");

    private String name;
    private String type;

    NoticeCategory(String name, String type) {
        this.name = name;
        this.type = type;
    }

}

