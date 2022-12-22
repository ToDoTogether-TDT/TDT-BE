package TDT.backend.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_NOT_EXISTS(404, "존재하지 않는 회원입니다."),
    POST_NOT_EXISTS(404, "존재하지 않는 글입니다."),

    TEAM_NOT_EXISTS(404, "존재하지 않는 팀입니다."),

    UNAUTHORIZED_ERROR(401, "권한이 없습니다"),;

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
