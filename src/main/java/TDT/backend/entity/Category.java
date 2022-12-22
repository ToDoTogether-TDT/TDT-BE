package TDT.backend.entity;

import lombok.Getter;

@Getter
public enum Category {


    PROGRAMMING("프로그래밍", "스터디"),
    EMPLOYMENT("취업", "스터디"),
    EXAM("시험", "스터디"),
    HOBBY("취미", "스터디"),
    LANGUAGE("어학", "스터디"),
    READING("독서", "스터디"),
    ETC("기타", "스터디"),

    DAILY("일상", "게시판"),
    WORRY("고민", "게시판"),
    PROMOTION("스터디 홍보", "게시판");

    private String name;
    private String type;

    Category(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
