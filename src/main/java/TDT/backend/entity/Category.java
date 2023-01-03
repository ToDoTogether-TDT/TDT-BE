package TDT.backend.entity;

import lombok.Getter;

@Getter
public enum Category {


    programming("프로그래밍", "스터디"),
    job("취업", "스터디"),
    exam("시험", "스터디"),
    hobby("취미", "스터디"),
    language("어학", "스터디"),
    book("독서", "스터디"),
    etc("기타", "스터디"),

    daily("일상", "게시판"),
    worry("고민", "게시판"),
    promotion("스터디 홍보", "게시판");

    private String name;
    private String type;

    Category(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
