package TDT.backend.entity;

import lombok.Getter;

@Getter
public enum Category {


    PROGRAMMING("프로그래밍", 1),
    EMPLOYMENT("취업", 2),
    EXAM("시험", 3),
    HOBBY("취미", 4),
    LANGUAGE("어학", 5),
    READING("독서", 6),
    ETC("기타", 7);

    private String name;
    private int num;

    Category(String name, int num) {
        this.name = name;
        this.num = num;
    }

}
