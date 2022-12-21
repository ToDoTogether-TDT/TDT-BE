package TDT.backend.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_NOT_EXISTS(404, "존재하지 않는 회원입니다."),
    POST_NOT_EXISTS(404, "존재하지 않는 글입니다.");


    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
